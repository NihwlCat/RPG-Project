import { diceRoll } from 'core/utils/requests'
import './AttributeCard.scss'

type Props = {
    name: string;
    value: number;
    belongsTo: string;
    onAction: (name: string, value: number, belongsTo: string) => void;
    isEditing: boolean;
}

const AttributeCard = ({name, value, onAction, belongsTo, isEditing}:Props) => {
    return <div className="attribute-container">
        <span className="attribute-name" onClick={() => diceRoll(name)}>{name}</span>
        <input className="attribute-value"  disabled={isEditing} type="number" value={value} name={name} onChange={event => onAction(event.target.name, Number(event.target.value), belongsTo)}/>
    </div> 
}

export default AttributeCard;
