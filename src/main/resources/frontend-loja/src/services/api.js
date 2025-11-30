import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

api.interceptors.request.use((config) => {
    const user = localStorage.getItem('user');
    if (user) {
        const userData = JSON.parse(user);
        if (userData.token) {
            config.headers.Authorization = `Bearer ${userData.token}`;
        }
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

// Se der erro 403 (Forbidden/Token Inválido), desloga o usuário
api.interceptors.response.use(response => response, error => {
    if (error.response && (error.response.status === 403 || error.response.status === 401)) {
        localStorage.removeItem('user');
        window.location.href = '/';
    }
    return Promise.reject(error);
});

export default api;