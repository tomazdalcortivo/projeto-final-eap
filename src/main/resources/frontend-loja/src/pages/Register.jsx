import {useState} from 'react';
import api from '../services/api';
import {useNavigate} from 'react-router-dom';

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
            navigate('/');
        } catch (error) {
            if (error.response && error.response.status === 400) {
                setErrors(error.response.data);
            } else {
                alert("Erro ao cadastrar. Verifique os dados.");
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
                {errors.name && <p className="error-text">{errors.name}</p>}

                <input
                    name="cpf"
                    placeholder="CPF"
                    onChange={handleChange}
                    required
                />
                {errors.cpf && <p className="error-text">{errors.cpf}</p>}

                <input
                    name="phone"
                    placeholder="Telefone"
                    onChange={handleChange}
                    required
                />
                {errors.phone && <p className="error-text">{errors.phone}</p>}

                <input
                    name="address"
                    placeholder="Endereço"
                    onChange={handleChange}
                    required
                />
                {errors.address && <p className="error-text">{errors.address}</p>}

                <button type="submit">Cadastrar</button>
            </form>
        </div>
    );
}

export default Register;