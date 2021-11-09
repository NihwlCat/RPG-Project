import { diceRoll } from 'core/utils/requests'
import './ExpositionBox.scss'


type Props = {
    control: number;
    awakening: number;
    onAction: (event: React.ChangeEvent<HTMLInputElement>) => void;
    disable: boolean;
}

const ExpositionBox = ({ control, awakening, onAction, disable }:Props) => {
    const total = Number(control) + Number (awakening)
    return <div className="exposition-box-container">
            <div className="exposition-info-container">
                <div className="exposition-info">
                    <span onClick={() => diceRoll("EXPOSIÇÃO")}>EXPOSIÇÃO</span>
                    <span>{total}</span>
                </div>
                <div className="exposition-values-container">
                    <div className="exposition-values">
                        <span>DESPERTAR</span>
                        <input type="number" value={awakening} name="awakening" disabled={disable} onChange={event => onAction(event)}/>
                    </div>
                    <div className="exposition-values">
                        <span>CONTROLE</span>
                        <input type="number" value={control} name="control" disabled={disable} onChange={event => onAction(event)}/>
                    </div>
                </div>
            </div>
        <div className="exposition-progress-bar">
            <div style={{width: `${Math.round((awakening / total) * 100)}%`, backgroundColor: 'rgba(116, 77, 148, 0.4)'}}></div>
            <div style={{width: `${Math.round((control / total) * 100)}%`, backgroundColor: 'rgba(251, 227, 168, 0.4)'}}></div>
        </div>
    </div>
}

export default ExpositionBox;