import { makePrivateRequest } from 'core/utils/requests';
import { Hash, Sheet } from 'core/utils/types';
import { useEffect, useState } from 'react';
import AttributeCard from './AtributteCard/AttributeCard';
import './SheetPage.scss'
import { ReactComponent as EditIcon} from 'core/images/_edit-icon.svg'
import { ReactComponent as SaveIcon} from 'core/images/_save-icon.svg'

const SheetPage = () => {
    const [sheet,setSheet] = useState<Sheet>()
    const [payload, setPayload] = useState<Hash>({})

    const [isEditingBasic, setIsEditingBasic] = useState(false)
    const [isEditingGeneral, setIsEditingGeneral] = useState(false)
    const [isEditingOffensive, setIsEditingOffensive] = useState(false)


    const onSave = (location: string) => {
        makePrivateRequest({url: '/characters/profile', method: 'PATCH', data: payload, params: {resource: location}})
        setPayload({})
    }

    const onChangeAction = (name: string, value: number, belongsTo: string) => {
        if(belongsTo === "basic" && sheet?.basic){
            const atts = {...sheet.basic, [name]: value}
            setSheet({...sheet, basic: atts})
            setPayload({...payload, [name]: value})
        }
        if(belongsTo === "general" && sheet?.general){
            const atts = {...sheet.general, [name]: value}
            setSheet({...sheet, general: atts})
            setPayload({...payload, [name]: value})
        }
        if(belongsTo === "offensive" && sheet?.offensive){
            const atts = {...sheet.offensive, [name]: value}
            setSheet({...sheet, offensive: atts})
            setPayload({...payload, [name]: value})
        }
    }

    useEffect(() => {
        makePrivateRequest({url: '/characters/profile/resources', params: {resource: 'sheet'}})
        .then(response => {
            setSheet(response.data);
        })
    },[])

    return <div className="sheet-atts-container">
        <div className="sheet-atts-fields">
        {isEditingBasic ? 
                <SaveIcon className="sheet-alterate-icon" onClick={() => {setIsEditingBasic(false); onSave("basic")}}/> 
                : <EditIcon className="sheet-alterate-icon" onClick={() => setIsEditingBasic(true)}/>}
            <p>ATRIBUTOS BÁSICOS</p>

            {sheet?.basic &&  Object.keys(sheet.basic).map(key => (
                <AttributeCard isEditing={!isEditingBasic} key={key} name={key} value={sheet.basic[key]} onAction={onChangeAction} belongsTo="basic"/>
            ))}
        </div>
        <div className="sheet-atts-fields">
        {isEditingGeneral ? 
                <SaveIcon className="sheet-alterate-icon" onClick={() => {setIsEditingGeneral(false); onSave("general")}}/> 
                : <EditIcon className="sheet-alterate-icon" onClick={() => setIsEditingGeneral(true)}/>}
            <p>ATRIBUTOS GERAIS</p>
            {sheet?.general &&  Object.keys(sheet.general).map(key => (
                <AttributeCard isEditing={!isEditingGeneral} key={key} name={key} value={sheet.general[key]} onAction={onChangeAction} belongsTo="general"/>
            ))}
        </div>
        <div className="sheet-atts-fields">
        {isEditingOffensive ? 
                <SaveIcon className="sheet-alterate-icon" onClick={() => {setIsEditingOffensive(false); onSave("offensive")}}/> 
                : <EditIcon className="sheet-alterate-icon" onClick={() => setIsEditingOffensive(true)}/>}
            <p>ATRIBUTOS OFENSIVOS</p>
            {sheet?.offensive &&  Object.keys(sheet.offensive).map(key => (
                <AttributeCard isEditing={!isEditingOffensive} key={key} name={key} value={sheet.offensive[key]} onAction={onChangeAction} belongsTo="offensive"/>
            ))}
        </div>
    </div>
}

export default SheetPage;