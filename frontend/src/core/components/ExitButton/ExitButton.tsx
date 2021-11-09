import './ExitButton.scss'
import { ReactComponent as ExitImage } from 'core/images/_exit-button.svg'
import { makeLogout } from 'core/utils/requests';

const ExitButton = () => {
    return <div className="exit-container" onClick={() => makeLogout()}>
        <ExitImage/>
        <span>Sair</span>
    </div>
}

export default ExitButton;