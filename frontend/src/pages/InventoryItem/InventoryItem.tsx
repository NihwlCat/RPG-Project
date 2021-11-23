import { makePrivateRequest } from 'core/utils/requests'
import { Item } from 'core/utils/types'
import { useCallback, useEffect, useState } from 'react'
import Select from 'react-select'
import 'core/InventorySelect.scss'
import ItemCard from 'core/components/ItemCard/ItemCard'
import Modal from 'core/components/Modal/Modal'


const Items = [
    {value: 'RANGED_WEAPON', label: 'Arma Ranged'},
    {value: 'MELEE_WEAPON', label: 'Arma Melee'},
    {value: 'COMMON', label: 'Item Comum'}
]

type ResponseBody = {
    id: string;
    items: Item[];
}

type Props = {
    title: string;
}

const InventoryItem = ({title}: Props) => {
    const [items, setItems] = useState<ResponseBody>()
    const [isCreating, setIsCreating] = useState(false)
    const [payload, setPayload] = useState<Item>({
        id: 0,
        description: '',
        type: 'COMMON',
        stringType: ''
    })

    const getItems = useCallback(() => {
        makePrivateRequest({ url: '/characters/profile/resources', params: {resource: 'items'}})
        .then(response => setItems(response.data))
    },[])

    useEffect(() => {
        getItems();
    },[getItems])

    const onUpdateItem = (data: Item) => {
        setPayload(data)
        setIsCreating(true)
    }

    const onDeleteItem = (data: Item) => {
        makePrivateRequest({ url: '/characters/profile', params: {resource: 'items'}, method:'DELETE' , data})
        .then(() => getItems()) 
    }

    const onChangeValue = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setPayload({...payload, [name]: value})
    }

    const onChangeSelect = (type?: string) => {
        if(type){
            setPayload({...payload, type})
        }
    }

    const onInsertItem = () => {

        if(payload.id === 0 && items){
            const ids = items.items.map(i => i.id)
            const id = Math.max.apply(null, ids) + 1
            makePrivateRequest({ url: '/characters/profile', params: {resource: 'items'}, method:'PATCH' , data: {...payload, id}})
            .then(() => getItems())
        } else {
            makePrivateRequest({ url: '/characters/profile', params: {resource: 'items'}, method:'PATCH' , data: payload})
            .then(() => getItems())
        }

        setPayload({
            id: 0,
            description: '',
            type: '',
            stringType: ''
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
                    <span>DESCRIÇÃO</span>
                    <textarea value={payload.description} name="description" onChange={event => onChangeValue(event)}/>
                </div>
                <div className="spell-edit-field-select">
                    <span>TIPO</span>
                    <Select 
                        placeholder={"Selecione"}
                        defaultValue={Items.find(rune => rune.value === payload.type)}
                        options={Items} 
                        classNamePrefix="runes-select" 
                        onChange={event => onChangeSelect(event?.value)}/>
                </div>
            </Modal>
        )}
        {items && (
           <div className="inventory-base-wrapper">
               {items.items.map(item => (
                    <ItemCard item={item} key={item.id} onUpdate={onUpdateItem} onDelete={onDeleteItem}/>
               ))}
            </div> 
        )}
    </div>
}

export default InventoryItem;