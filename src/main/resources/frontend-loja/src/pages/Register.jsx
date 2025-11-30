import {useState} from 'react';
import api from '../services/api';
import {useNavigate, Link} from 'react-router-dom'; // Importei Link aqui

function Register() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: '',
        password: '',
        name: '',
        cpf: '',
        phone: '',
        address: ''
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});

        const payload = {
            email: formData.email,
            password: formData.password,
            client: {
                name: formData.name,
                cpf: formData.cpf,
                phone: formData.phone,
                address: formData.address
            }
        };

        try {
            await api.post('/account/register', payload);
            alert("Cadastro realizado com sucesso!"); // Feedback visual
            navigate('/');
        } catch (error) {
            console.error("Erro no registro:", error); // Log para debug no console
            if (error.response) {
                if (error.response.status === 400) {
                    // Se for erro de validação (campos inválidos)
                    setErrors(error.response.data);
                } else {
                    // Outros erros (500, 403, etc)
                    alert(`Erro ao cadastrar: ${error.response.data?.message || 'Tente novamente.'}`);
                }
            } else {
                alert("Erro de conexão com o servidor.");
            }
        }
    };

    return (
        <div className="auth-container">
            <h2 className="title">Criar Conta</h2>

            <form onSubmit={handleSubmit}>

                <input
                    name="email"
                    placeholder="Email"
                    onChange={handleChange}
                    required
                />
                {errors.email && <p className="error-text">{errors.email}</p>}

                <input
                    name="password"
                    type="password"
                    placeholder="Senha"
                    onChange={handleChange}
                    required
                />
                {errors.password && <p className="error-text">{errors.password}</p>}

                <h4 className="subtitle">Dados Pessoais</h4>

                <input
                    name="name"
                    placeholder="Nome Completo"
                    onChange={handleChange}
                    required
                />
                {errors['client.name'] && <p className="error-text">{errors['client.name']}</p>}

                <input
                    name="cpf"
                    placeholder="CPF"
                    onChange={handleChange}
                    required
                />
                {errors['client.cpf'] && <p className="error-text">{errors['client.cpf']}</p>}

                <input
                    name="phone"
                    placeholder="Telefone"
                    onChange={handleChange}
                    required
                />
                {errors['client.phone'] && <p className="error-text">{errors['client.phone']}</p>}

                <input
                    name="address"
                    placeholder="Endereço"
                    onChange={handleChange}
                    required
                />
                {errors['client.address'] && <p className="error-text">{errors['client.address']}</p>}

                <button type="submit">Cadastrar</button>
            </form>

            <p className="auth-footer">
                Já tem uma conta? <Link className="link" to="/">Entrar</Link>
            </p>
        </div>
    );
}

export default Register;