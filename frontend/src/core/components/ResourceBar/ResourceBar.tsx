import { NavLink } from 'react-router-dom';
import './ResourceBar.scss'


const ResourceBar = () => {
    return <div>
        <h2 className="resource-bar-title">RECURSOS</h2>
        <nav>
            <ul className="resource-bar">
                <li style={{marginTop: '10px'}}>
                    <NavLink to="/player/annotations" activeClassName="active" exact className="resource-navbar-item">Anotações</NavLink>
                </li>
                <li style={{marginTop: '10px'}}>
                    <NavLink to="/player/history" activeClassName="active" exact className="resource-navbar-item">História</NavLink>
                </li>
                <li style={{marginTop: '10px'}}>
                    <NavLink to="/player/items" activeClassName="active" exact className="resource-navbar-item">Inventário</NavLink>
                </li>
                <li style={{marginTop: '10px'}}>
                    <NavLink to="/player/spells" activeClassName="active" exact className="resource-navbar-item">Feitiços</NavLink>
                </li>
                <li style={{marginTop: '10px'}}>
                    <NavLink to="/player/sheet" activeClassName="active" exact className="resource-navbar-item">Ficha</NavLink>
                </li>
            </ul>
        </nav>

    </div>

}

export default ResourceBar;