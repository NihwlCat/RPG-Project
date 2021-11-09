import './Auth.scss'
import _authImage from 'core/images/_auth-image.svg'
import { makeLogin, recoverTokenData, storageToken } from 'core/utils/requests'
import { useState } from 'react'
import { useHistory } from 'react-router-dom'


const Auth = () => {
    const [username, setUsername] = useState('')
    const [error, setError] = useState(false)
    const history = useHistory()


    const loginAction = () => {
        makeLogin(username)
        .then(response => {
            setError(false)
            storageToken(response.data)
            const from = recoverTokenData().role === "JOGADOR" ? { pathname: '/player' } : { pathname: '/master' }
            history.replace(from)
        })
        .catch(() => setError(true))
    }

    return <div className="auth-section">
        <div>
            <img src={_authImage} alt="Luceranos" className="auth-image" />
        </div>
        <div className="auth-box">
            <p>Olá investigador, <br/> Insira seu código para acessar a ficha do seu personagem.</p>
            {error && <span style={{color: '#7251B5'}}>Código inválido</span>}
            <input value={username} onChange={event => setUsername(event.target.value)}/>
            <button onClick={loginAction}>ACESSAR</button>
        </div>
    </div>

}

export default Auth;