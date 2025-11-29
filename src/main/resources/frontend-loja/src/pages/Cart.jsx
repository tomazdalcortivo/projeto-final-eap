import {useEffect, useState} from 'react';
import api from '../services/api';
import {useNavigate} from 'react-router-dom';

function Cart() {
    const navigate = useNavigate();
    const [items, setItems] = useState([]);
    const user = JSON.parse(localStorage.getItem('user'));

    useEffect(() => {
        if (!user) {
            navigate('/');
            return;
        }
        loadCart();
    }, []);

    const loadCart = async () => {
        try {
            // Ajuste aqui para usar o ID correto do cliente
            const response = await api.get(`/cart/${user.client.id}`);
            setItems(response.data);
        } catch (error) {
            console.error("Erro ao carregar carrinho", error);
        }
    };

    const handleRemove = async (id) => {
        await api.delete(`/cart/${id}`);
        loadCart();
    };

    const handleCheckout = async () => {
        try {
            await api.post(`/cart/checkout/${user.client.id}`);
            alert('Compra realizada com sucesso!');
            setItems([]);
            navigate('/products');
        } catch {
            alert('Erro ao finalizar compra');
        }
    };

    const totalCart = items.reduce((acc, item) => acc + item.total, 0);

    return (
        <div className="auth-container" style={{maxWidth: '800px'}}>
            <h2 className="title">Meu Carrinho</h2>

            {items.length === 0 ? (
                <p>Seu carrinho está vazio.</p>
            ) : (
                <div className="product-grid" style={{display: 'flex', flexDirection: 'column', gap: '10px'}}>
                    {items.map((item) => (
                        <div key={item.id} className="product-card"
                             style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
                            <div>
                                <strong>{item.productName}</strong>
                                <p>Qtd: {item.quantity} x R$ {item.price.toFixed(2)}</p>
                            </div>
                            <div style={{textAlign: 'right'}}>
                                <p style={{fontWeight: 'bold', color: '#2563eb'}}>Total: R$ {item.total.toFixed(2)}</p>
                                <button
                                    onClick={() => handleRemove(item.id)}
                                    style={{
                                        backgroundColor: '#ef4444',
                                        padding: '5px 10px',
                                        fontSize: '0.8rem',
                                        width: 'auto'
                                    }}
                                >
                                    Remover
                                </button>
                            </div>
                        </div>
                    ))}

                    <div style={{
                        marginTop: '20px',
                        borderTop: '2px solid #e5e7eb',
                        paddingTop: '10px',
                        textAlign: 'right'
                    }}>
                        <h3>Total Geral: R$ {totalCart.toFixed(2)}</h3>
                        <button onClick={handleCheckout} style={{width: '200px'}}>Finalizar Compra</button>
                    </div>
                </div>
            )}

            <button
                onClick={() => navigate('/products')}
                style={{backgroundColor: '#6b7280', marginTop: '20px'}}
            >
                Voltar para Produtos
            </button>
        </div>
    );
}

export default Cart;