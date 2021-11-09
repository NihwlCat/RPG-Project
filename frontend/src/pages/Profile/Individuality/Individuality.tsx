import { diceRoll } from 'core/utils/requests';
import './Individuality.scss'

type Props = {
    value: number;
    description: string;
    onAction: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
    disable: boolean;
}

const Individuality = ({value, description, onAction, disable}: Props) => {



    return <div className="individuality-container">
        <div className="individuality-info">
            <span onClick={() => diceRoll("INDIVIDUALIDADE")}>INDIVIDUALIDADE</span>
            <input type="number" value={value} name="value" disabled={disable} onChange={event => onAction(event)}/>
        </div>
        <textarea className="individuality-description" name="description" disabled={disable} value={description} onChange={event => onAction(event)}></textarea>
    </div>
}

export default Individuality;