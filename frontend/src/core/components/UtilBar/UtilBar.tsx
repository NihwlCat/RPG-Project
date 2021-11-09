import './UtilBar.scss'


// <a href="http://www.external-domain.com/" target='_blank' rel="noreferrer" className="util-bar-item">Resumo</a>
const UtilBar = () => {
    return <div className="util-bar-container">
        <h2 className="util-bar-title">ÚTEIS</h2>
        <ul className="resource-bar">
            <li style={{marginTop: '10px'}}>
                <a href="#B" className="util-bar-item" onClick={() => alert('Amanhã meio dia ta pronto')}>Resumo</a>
            </li>
            <li style={{marginTop: '10px'}}>
                <a href="#A" className="util-bar-item" onClick={() => alert('Amanhã meio dia ta pronto')}>Regras</a>
            </li>
        </ul>
    </div>
}

export default UtilBar;