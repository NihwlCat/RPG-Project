import './ItemCard.scss'
import { ReactComponent as EditIcon} from 'core/images/_edit-icon.svg'
import { ReactComponent as DeleteIcon} from 'core/images/_trash-icon.svg'
import { Item } from 'core/utils/types'

type Props = {
    item: Item;
    onUpdate: (data: Item) => void;
    onDelete: (data: Item) => void;
}

const ItemCard = ({item, onUpdate, onDelete}:Props) => {
    return <div className="item-card-container"> 
        <div className="item-card-alterate-icon">
            <EditIcon onClick={() => onUpdate(item)}/>
            <DeleteIcon onClick={() => onDelete(item)}/>
        </div>
        <div className="item-card-title">
            <span>
                {item.stringType}
            </span>
        </div>
        <textarea className="item-card-description" value={item.description} disabled/>
    </div>


}

export default ItemCard;