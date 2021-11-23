import './Modal.scss'

type Props = {
    children: React.ReactNode;
    onClose: () => void;
    onModalAction: () => void;
}

const Modal = ({children, onClose, onModalAction}:Props) => {
    return <div className="modal-container">
                <div className="main-modal-body">
                    <div className="main-modal-content">
                        {children}
                    </div>
                    <div className="button-content">
                        <button className="btn btn-lg btn-light go-back" onClick={onClose}>Voltar</button>
                        <button className=" btn btn-primary btn-lg go-confirm" onClick={onModalAction}>Confirmar</button>
                    </div>
                </div>
        </div>
}

export default Modal;