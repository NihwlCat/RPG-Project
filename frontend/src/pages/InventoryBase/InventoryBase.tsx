import SpellCard from 'core/components/SpellCard/SpellCard';
import { makePrivateRequest } from 'core/utils/requests';
import { useCallback, useEffect, useState } from 'react';
import './InventoryBase.scss'
import 'core/InventorySelect.scss'
import {Spell} from 'core/utils/types'
import Select from 'react-select';
import Modal from 'core/components/Modal/Modal';

const Runes = [
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Fire.png', label: 'Ignis'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Wind.png', label: 'Ventus'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Space.png', label: 'Locus'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Nature.png', label: 'Natura'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Illusion.png', label: 'Illusio'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Invocation.png', label: 'Invocatio'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Teleportation.png', label: 'Obsessio'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Healing.png', label: 'Relidor'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Decomposition.png', label: 'Compositione'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Water.png', label: 'Aqua'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Gravity.png', label: 'Gravitas'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Light.png', label: 'Luminifera'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Death.png', label: 'Mortem'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Curse.png', label: 'Maledictio'},
    {value: 'https://raw.githubusercontent.com/NihwlCat/RPG-Project/master/resources/Runes/Chaos.png', label: 'Tenebris'}
]

type Props = {
    title: string;
}

type ResponseBody = {
    id: string;
    spells: Spell[];
}

const InventoryBase = ({title}:Props) => {
    const [spells, setSpells] = useState<ResponseBody>()
    const [isCreating, setIsCreating] = useState(false)
    const [payload, setPayload] = useState<Spell>({
        name: '',
        description: '',
        imgUrl: ''
    })

    const getItems = useCallback(() => {
        makePrivateRequest({ url: '/characters/profile/resources', params: {resource: 'spells'}})
        .then(response => setSpells(response.data))
    },[])

    useEffect(() => {
        getItems();
    },[getItems])

    const onUpdateItem = (data: Spell) => {
        setPayload(data)
        setIsCreating(true)
    }

    const onDeleteItem = (data: Spell) => {
        makePrivateRequest({ url: '/characters/profile', params: {resource: 'spells'}, method:'DELETE' , data})
        .then(() => getItems()) 
    }

    const onChangeValue = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value = event.target.value;

        setPayload({...payload, [name]: value})
    }

    const onChangeSelect = (imgUrl?: string) => {
        if(imgUrl){
            setPayload({...payload, imgUrl})
        }
    }

    const onInsertItem = () => {
        makePrivateRequest({ url: '/characters/profile', params: {resource: 'spells'}, method:'PATCH' , data: payload})
        .then(() => getItems())
        setPayload({
            name: '',
            description: '',
            imgUrl: ''
        })
    }

    return <div>
        <div className="inventory-base-header">
            <p>{title}</p>
            <button onClick={() => setIsCreating(true)}>ADICIONAR</button>
            
        </div>
        {isCreating && (
            <Modal onClose={() => setIsCreating(false)} onModalAction={() => {onInsertItem(); setIsCreating(false)}}>
                    <div className="spell-edit-field">
                        <span>NOME</span>
                        <input type="text" value={payload.name} name="name" onChange={event => onChangeValue(event)}/>
                    </div>
                    <div className="spell-edit-field">
                        <span>DESCRIÇÃO</span>
                        <textarea value={payload.description} name="description" onChange={event => onChangeValue(event)}/>
                    </div>
                    <div className="spell-edit-field-select">
                        <span>RUNA</span>
                        <Select 
                            placeholder={"Selecione"}
                            defaultValue={Runes.find(rune => rune.value === payload.imgUrl)}
                            options={Runes} 
                            classNamePrefix="runes-select" 
                            onChange={event => onChangeSelect(event?.value)}/>
                    </div>
            </Modal>
        )}
        {spells && (
           <div className="inventory-base-wrapper">
               {spells.spells.map(spell => (
                    <SpellCard spell={spell} key={spell.name} onUpdate={onUpdateItem} onDelete={onDeleteItem}/>
               ))}
            </div> 
        )}
    </div>
}

export default InventoryBase;