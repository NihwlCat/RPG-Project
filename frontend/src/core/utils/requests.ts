import axios, { AxiosRequestConfig } from "axios"
import jwtDecode from "jwt-decode"
import { createBrowserHistory } from 'history'
import { toast } from "react-toastify";

export const newHistory = createBrowserHistory();

//const BASE_URL = 'http://localhost:8081'
const BASE_URL = 'https://rpg-nihwl.herokuapp.com'

type TokenDecoded = {
    sub: string;
    role: string;
    exp: number;
    name: string;
}

export const storageToken = (token: string) => {
    localStorage.setItem('token', token)
}

export const recoverTokenData = () => {
    try {
        const token = localStorage.getItem('token') ?? ''
        return jwtDecode(token) as TokenDecoded;
    } catch(error) {
        return { } as TokenDecoded;
    }
}

export const makeRequest = (params: AxiosRequestConfig) => {
    return axios({
        ...params,
        baseURL: BASE_URL
    })
}

export const makePrivateRequestText = (params: AxiosRequestConfig) => {
    const token = localStorage.getItem('token') ?? ''
    const headers = { 
        Authorization: `Bearer ${token}`,
        'Content-Type' : 'text/plain' 
    }

    return makeRequest({ ...params, headers})
}

export const diceRoll = (attribute: string) => {
    makePrivateRequestText({url: '/interactions/bot', method:'POST', data: attribute})
    .then(r => {
        const result = String(r.data).split(',')
        toast.dark(result[0] + ", " + result[1])})
}

export const makeLogin = (username: string) => {
    return makeRequest({url: '/login', method: 'POST', params: { username }});
}

export const makePrivateRequest = (params: AxiosRequestConfig) => {
    const token = localStorage.getItem('token') ?? ''
    const headers = { Authorization: `Bearer ${token}` }

    return makeRequest({ ...params, headers})
}

const isTokenValid = () => {
    const { exp } =  recoverTokenData();
    return Date.now() <= exp * 1000
}

export const isAuthenticated = () => {
    const tokenDecoded = recoverTokenData();
    return tokenDecoded && isTokenValid();
}

export const isAllowedByRole = (roles: string[] = []) => {
    const  { role } = recoverTokenData(); 
    return role !== null && roles.includes(role);
}

export const makeLogout = () => {
    localStorage.removeItem('token')
    newHistory.replace('/')
}

axios.interceptors.response.use((response) => {return response}, (error) => {
    if(error.response.status === 401){
        newHistory.push('/')
        makeLogout()
    }
    return Promise.reject(error);
})
