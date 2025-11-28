import { useEffect, useState } from 'react';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

function Products() {
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [newProduct, setNewProduct] = useState({ name: '', description: '', price: '' });
    const user = JSON.parse(localStorage.getItem('user'));

    useEffect(() => {
        if (!user) {
            navigate('/');
            return;
        }
        loadProducts();
    }, []);

    const loadProducts = async () => {
        const response = await api.get('/product');
        setProducts(response.data.content || []);
    };

    const handleCreateProduct = async (e) => {
        e.preventDefault();

        const payload = {
            name: newProduct.name,
            description: newProduct.description,
            price: parseFloat(newProduct.price),
            clientId: user.clientId
        };

        try {
            await api.post('/product', payload);
            setNewProduct({ name: '', description: '', price: '' });
            loadProducts();
        } catch {
            alert('Erro ao criar produto');
        }
    };

    return (
        <div style={{ padding: "20px", maxWidth: "900px", margin: "auto" }}>
            <header style={{
                display: 'flex', justifyContent: 'space-between', alignItems: 'center',
                marginBottom: '20px'
            }}>
                <h2>Painel de Produtos</h2>
                <button onClick={() => {
                    localStorage.clear();
                    navigate('/');
                }}>Sair</button>
            </header>

            <div style={{ display: 'flex', gap: '30px' }}>

                <div style={{ flex: 1 }}>
                    <h3>Novo Produto</h3>

                    <form onSubmit={handleCreateProduct} style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
                        <input placeholder="Nome do Produto"
                               value={newProduct.name}
                               onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })} />

                        <input placeholder="Descrição"
                               value={newProduct.description}
                               onChange={(e) => setNewProduct({ ...newProduct, description: e.target.value })} />

                        <input type="number" placeholder="Preço"
                               value={newProduct.price}
                               onChange={(e) => setNewProduct({ ...newProduct, price: e.target.value })} />

                        <button type="submit">Adicionar Produto</button>
                    </form>
                </div>

                <div style={{ flex: 2 }}>
                    <h3>Lista de Produtos</h3>
                    {products.map((p) => (
                        <div className="product-card" key={p.id}>
                            <strong>{p.name}</strong>
                            <p>{p.description}</p>
                            <p><b>R$ {p.price}</b></p>
                            <small>Vendedor: {p.clientName}</small>
                        </div>
                    ))}
                </div>

            </div>
        </div>
    );
}

export default Products;
