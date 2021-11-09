import './Attributes.scss'


 type Props = {
     move: number;
     defense: number;
     vigor: number;
     onAction: (event: React.ChangeEvent<HTMLInputElement>) => void;
     disable: boolean;
 }
const Attributes = ({move, defense, vigor, onAction, disable}: Props) => {

    return <div className="profile-atts-container">
        <div className="profile-atts">
            <span>MOVIMENTO</span>
            <input type='number' disabled={disable} value={move} name="movement" onChange={event => onAction(event)}/>
        </div>
        <div className="profile-atts">
            <span>VIGOR</span>
            <input type='number' disabled={disable} value={vigor} name="vigor" onChange={event => onAction(event)}/>
        </div>
        <div className="profile-atts">
            <span>DEFESA</span>
            <input type='number' disabled={disable} value={defense} name="defense" onChange={event => onAction(event)}/>
        </div>
    </div>

}

export default Attributes;