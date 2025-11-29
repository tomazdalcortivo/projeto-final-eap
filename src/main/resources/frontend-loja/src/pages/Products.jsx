import {useEffect, useState} from 'react';
import api from '../services/api';
import {useNavigate} from 'react-router-dom';

function Products() {
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [cartItems, setCartItems] = useState([]);

    const [newProduct, setNewProduct] = useState({name: '', description: '', price: '', stock: ''});
    const [quantities, setQuantities] = useState({});

    const user = JSON.parse(localStorage.getItem('user'));

    useEffect(() => {
        if (!user) {
            navigate('/');
            return;
        }
        loadData();
    }, []);

    const loadData = async () => {
        await Promise.all([loadProducts(), loadCart()]);
    };

    const loadProducts = async () => {
        try {
            const response = await api.get('/product');
            setProducts(response.data.content || []);
        } catch (error) {
            console.error("Erro ao carregar produtos", error);
        }
    };

    const loadCart = async () => {
        try {
            const response = await api.get(`/cart/${user.client.id}`);
            setCartItems(response.data || []);
        } catch (error) {
            console.error("Erro ao carregar carrinho", error);
        }
    };

    const handleCreateProduct = async (e) => {
        e.preventDefault();
        const payload = {
            name: newProduct.name,
            description: newProduct.description,
            price: parseFloat(newProduct.price),
            stock: parseInt(newProduct.stock),
            clientId: user.client.id
        };
        try {
            await api.post('/product', payload);
            setNewProduct({name: '', description: '', price: '', stock: ''});
            loadProducts();
        } catch {
            alert('Erro ao criar produto');
        }
    };

    const handleQuantityChange = (productId, value) => {
        setQuantities({...quantities, [productId]: parseInt(value)});
    };

    const addToCart = async (product, availableStock) => {
        const qty = quantities[product.id] || 1;

        if (qty > availableStock) {
            alert(`Você já tem itens no carrinho! Só restam ${availableStock} unidades disponíveis.`);
            return;
        }

        try {
            await api.post('/cart/add', {
                clientId: user.client.id,
                productId: product.id,
                quantity: qty
            });
            alert('Produto adicionado ao carrinho!');
            await loadCart(); // Espera carregar o carrinho atualizado
            setQuantities({...quantities, [product.id]: 1}); // Reseta o input visual
        } catch (error) {
            const msg = error.response?.data?.message || 'Erro ao adicionar ao carrinho';
            alert(msg);
        }
    };

    // Função corrigida: Agora compara pelo ID do Produto!
    const getQuantityInCart = (product) => {
        // Encontra o item no carrinho comparando o productId
        const item = cartItems.find(i => i.productId === product.id);
        return item ? item.quantity : 0;
    };

    return (
        <div className="dashboard-container">
            <header className="dashboard-header">
                <h2>Painel de Produtos</h2>
                <div>
                    <button
                        onClick={() => navigate('/cart')}
                        style={{marginRight: '10px', width: 'auto', backgroundColor: '#10b981'}}
                    >
                        Ver Carrinho ({cartItems.length})
                    </button>
                    <button className="btn-logout" onClick={() => {
                        localStorage.clear();
                        navigate('/');
                    }}>Sair
                    </button>
                </div>
            </header>

            <div className="dashboard-content">
                <aside className="product-form-card">
                    <h3>Vender Produto</h3>
                    <form onSubmit={handleCreateProduct}>
                        <input
                            placeholder="Nome do Produto"
                            value={newProduct.name}
                            onChange={(e) => setNewProduct({...newProduct, name: e.target.value})}
                            required
                        />
                        <input
                            placeholder="Descrição"
                            value={newProduct.description}
                            onChange={(e) => setNewProduct({...newProduct, description: e.target.value})}
                            required
                        />
                        <div style={{display: 'flex', gap: '10px'}}>
                            <input
                                type="number" step="0.01" placeholder="Preço (R$)"
                                value={newProduct.price}
                                onChange={(e) => setNewProduct({...newProduct, price: e.target.value})}
                                required
                            />
                            <input
                                type="number" placeholder="Qtd. Estoque"
                                value={newProduct.stock}
                                onChange={(e) => setNewProduct({...newProduct, stock: e.target.value})}
                                required
                            />
                        </div>
                        <button type="submit">Anunciar Produto</button>
                    </form>
                </aside>

                <main>
                    <h3 style={{marginBottom: '20px'}}>Disponíveis para Compra</h3>
                    <div className="product-grid">
                        {products.map((p) => {
                            const qtyInCart = getQuantityInCart(p);
                            const available = p.stock - qtyInCart; // Cálculo correto do disponível

                            // Se disponível for 0 ou negativo, considera esgotado para o usuário
                            const isSoldOut = available <= 0;

                            // Texto do botão dinâmico
                            let buttonText = 'Adicionar';
                            if (p.stock === 0) buttonText = 'Esgotado';
                            else if (isSoldOut) buttonText = 'Máx. no Carrinho';

                            return (
                                <div className="product-card" key={p.id}>
                                    <div>
                                        <div style={{display: 'flex', justifyContent: 'space-between'}}>
                                            <strong>{p.name}</strong>
                                            <span style={{
                                                fontSize: '0.8rem',
                                                background: isSoldOut ? '#fee2e2' : '#d1fae5',
                                                color: isSoldOut ? '#991b1b' : '#065f46',
                                                padding: '2px 6px',
                                                borderRadius: '4px',
                                                height: 'fit-content'
                                            }}>
                                                Disp: {available}
                                            </span>
                                        </div>
                                        <p>{p.description}</p>
                                        <div className="product-price">R$ {p.price?.toFixed(2)}</div>
                                        <small style={{color: '#9ca3af'}}>Vendedor: {p.clientName}</small>

                                        {qtyInCart > 0 && (
                                            <small style={{
                                                display: 'block',
                                                color: '#2563eb',
                                                marginTop: '4px',
                                                fontWeight: 'bold'
                                            }}>
                                                {qtyInCart} unid. já no carrinho
                                            </small>
                                        )}
                                    </div>

                                    <div style={{marginTop: '15px', display: 'flex', gap: '8px'}}>
                                        <input
                                            type="number"
                                            min="1"
                                            max={available > 0 ? available : 1}
                                            value={quantities[p.id] || 1}
                                            onChange={(e) => handleQuantityChange(p.id, e.target.value)}
                                            style={{width: '60px', padding: '8px'}}
                                            disabled={isSoldOut}
                                        />
                                        <button
                                            onClick={() => addToCart(p, available)}
                                            style={{marginTop: '0', fontSize: '0.9rem', opacity: isSoldOut ? 0.6 : 1}}
                                            disabled={isSoldOut}
                                        >
                                            {buttonText}
                                        </button>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </main>
            </div>
        </div>
    );
}

export default Products;