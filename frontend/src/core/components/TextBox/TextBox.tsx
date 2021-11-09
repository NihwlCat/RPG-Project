import './TextBox.scss'
import { ReactComponent as EditIcon} from 'core/images/_edit-icon.svg'
import { ReactComponent as SaveIcon} from 'core/images/_save-icon.svg'
import { useEffect, useState } from 'react'
import { makePrivateRequest, recoverTokenData } from 'core/utils/requests'

type Props = {
    description: string;
    queryType: string;
}

type History = {
    id: string;
    history: string;
}

type Annotation = {
    name: string;
    annotations: string;
}


const TextBox = ({description, queryType}:Props) => {
    const [isEditing, setIsEditing] = useState(false)
    const [entity, setEntity] = useState<Annotation | History>()

    useEffect(() => {
        if(queryType === "history"){
            makePrivateRequest({url: '/characters/profile/resources', params: {resource: queryType}})
            .then(response => setEntity(response.data as History))
        } else {
            makePrivateRequest({url: `/players/${recoverTokenData().sub.replace('#','%23')}`})
            .then(response => setEntity(response.data as Annotation))
        }
    },[queryType])

    const handleOnChange = (event: React.ChangeEvent<HTMLTextAreaElement> ) => {
        const value = event.target.value;
        if(queryType === "history"){
            const history : History = { id: "", history: value } 
            setEntity(history)
        } else {
            const annotations : Annotation = { name: "", annotations: value }
            setEntity(annotations)
        }
    }

    const onSave = () => {
        if(queryType === "history"){
            makePrivateRequest({url: '/characters/profile', params: {resource: queryType}, method: 'PATCH', data: entity as History})
        } else {
            makePrivateRequest({url: '/players', method: 'PATCH', data: entity as Annotation})
        }
    }

    return <div className="text-box-container">
        {isEditing ? 
                <SaveIcon className="text-alterate-icon" onClick={() => {setIsEditing(false); onSave()}}/> 
                : <EditIcon className="text-alterate-icon" onClick={() => setIsEditing(true)}/>}
        <span>{description}</span>
        <div>
            <div className="rich-text-container"></div>
            {
                entity && (
                    queryType === 'history' ? (<textarea value={(entity as History).history} disabled={!isEditing} onChange={event => handleOnChange(event)}/>) : (<textarea value={(entity as Annotation).annotations} disabled={!isEditing} onChange={event => handleOnChange(event)}/>)
                )
            }
        </div>
    </div>

}

export default TextBox;