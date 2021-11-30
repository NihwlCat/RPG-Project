import { recoverTokenData } from 'core/utils/requests'
import { Profile } from 'core/utils/types'
import Select, { SingleValue } from 'react-select'
import './Info.scss'
import './Select.scss'

type Props = {
    profile: Profile;
    isEditing: boolean;
    onAction: (event?: React.ChangeEvent<HTMLInputElement>, select?: SingleValue<{
        value: string;
        label: string;
    }> ) => void;
}

const Status = [
    {value: 'ALIVE', label: 'Vivo'},
    {value: 'DEAD', label: 'Morrendo'},
    {value: 'INJURED', label: 'Ferido'},
    {value: 'UNCONSCIOUS', label: 'Inconsciente'},
    {value: 'TRAUMATIZED', label: 'Traumatizado'}
]

const Info = ({profile, isEditing, onAction}:Props) => {

    return <div className="info-container">
        <div className="info-image" style={{backgroundImage: `url(${profile.imgUrl})`}}>
        </div>
        <div className="info-content">
            <div className="info-fields">
                <span>NOME</span>
                {isEditing ? <input type="text" value={profile.name} name="name" onChange={event => onAction(event)}/> : <p>{profile.name}</p>}
            </div>
            <div className="info-fields">
                <span>IDADE</span>
                {isEditing ? <input type="number" value={profile.age} name="age" onChange={event => onAction(event)}/> : <p>{profile.age} <span>Anos</span></p>}
            </div>
            <div className="info-fields">
                <span>ALINHAMENTO</span>
                {isEditing ? <input type="text" value={profile.alignment} name="alignment" onChange={event => onAction(event)}/> : <p>{profile.alignment}</p>}
            </div>
            <div className="info-fields">
                {isEditing ? <Select 
                placeholder={profile.statusVerbose}
                options={Status} 
                classNamePrefix="status-select" 
                onChange={event => onAction(undefined, event)}/> : <> <span>STATUS</span> <p>{profile.statusVerbose}</p> </>}
            </div>
            <div className="info-fields">
                <span>JOGADOR</span>
                <p style={{color: '#7251B5', fontWeight: 500}}>{recoverTokenData().name}</p>
            </div>
        </div>
    </div>
}

export default Info;