import {useState} from 'react';
import api from '../services/api';
import {useNavigate, Link} from 'react-router-dom';

function Login() {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/account/login', {email, password});
            localStorage.setItem('user', JSON.stringify(response.data));
            navigate('/products');
        } catch {
            alert('Email ou senha inválidos');
        }
    };

    return (
        <div className="auth-container">
            <h2 className="title">Login</h2>

            <form onSubmit={handleLogin}>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Senha"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                <button type="submit">Entrar</button>
            </form>

            <p className="auth-footer">
                Não tem conta? <Link className="link" to="/register">Cadastre-se</Link>
            </p>
        </div>
    );
}

export default Login;