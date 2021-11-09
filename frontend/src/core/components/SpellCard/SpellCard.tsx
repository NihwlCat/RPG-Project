import './SpellCard.scss'
import { ReactComponent as EditIcon} from 'core/images/_edit-icon.svg'
import { ReactComponent as DeleteIcon} from 'core/images/_trash-icon.svg'
import { Spell } from 'core/utils/types'

type Props = {
    spell: Spell;
    onUpdate: (data: Spell) => void;
    onDelete: (data: Spell) => void;
}

const SpellCard = ({spell, onUpdate, onDelete}: Props) => {
    return <div className="spell-card-container"> 
        <div className="spell-card-alterate-icon">
            <EditIcon onClick={() => onUpdate(spell)}/>
            <DeleteIcon onClick={() => onDelete(spell)}/>
        </div>
        <div className="spell-card-title">
            <img src={spell.imgUrl} alt="Runa" />
            <span>
                {spell.name}
            </span>
        </div>
        <div className="spell-card-description">
            <textarea value={spell.description} disabled/>
        </div>
    </div>
}

export default SpellCard;