import './Profile.scss'
import ExitButton from "core/components/ExitButton/ExitButton";
import Info from './Info/Info';
import UtilBar from 'core/components/UtilBar/UtilBar';
import ResourceBar from 'core/components/ResourceBar/ResourceBar';
import { ReactComponent as EditIcon} from 'core/images/_edit-icon.svg'
import { ReactComponent as SaveIcon} from 'core/images/_save-icon.svg'
import SealBox from './SealBox/SealBox';
import StatusBox from 'core/components/StatusBox/StatusBox';
import ExpositionBox from 'core/components/ExpositionBox/ExpositionBox';
import Attributes from './Attributes/Attributes';
import Individuality from './Individuality/Individuality';
import { Route, Switch } from 'react-router';
import { useEffect, useState } from 'react';
import { makePrivateRequest } from 'core/utils/requests';
import { Character } from 'core/utils/types';
import { SingleValue } from 'react-select';
import SheetPage from 'pages/Sheet/SheetPage';
import TextBox from 'core/components/TextBox/TextBox';
import InventoryBase from 'pages/InventoryBase/InventoryBase';
import InventoryItem from 'pages/InventoryItem/InventoryItem';


const Profile = () => {
    const [character, setCharacter] = useState<Character>()
    const [isEditing, setIsEditing] = useState(false)

    const onSaveAction = () => {
        const payload = {...character}
        makePrivateRequest({url: '/characters/profile', method:'PATCH', params: {resource: 'profile'}, data: payload})
    }

    useEffect(() => {
        makePrivateRequest({url: '/characters/profile'})
        .then(response => {setCharacter(response.data)})
    },[])


    const onBasicChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name;
        const value =  Number(event.target.value);

        if(character?.basic){
            const basics = {...character?.basic, [name]: value};
            setCharacter({...character, basic: basics})
        }
    }

    const onInfoChange = (event?: React.ChangeEvent<HTMLInputElement>, select?: SingleValue<{value: string; label: string}>) => {
        if(event){
            const name = event.target.name;
            const value =  event.target.value;

            if(character?.profile){
                const infos = {...character.profile, [name]: value}
                setCharacter({...character, profile: infos})
            }
        } else {
            if(character?.profile && select){
                const infos = {...character.profile, status: select.value, statusVerbose: select.label}
                setCharacter({...character, profile: infos})
            }
        }
    }



    const onIndividualityChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value =  event.target.value;

        if(character?.individuality){
            const indv = {...character.individuality, [name]: value}
            setCharacter({...character, individuality: indv})
        }
    }

    return <section className="content-section">
        <header className="profile-header">
            <div className="profile-header-exit">
                <ExitButton />
                {isEditing ? 
                <SaveIcon className="alterate-icon" onClick={() => { onSaveAction(); setIsEditing(false)}}/> 
                : <EditIcon className="alterate-icon" onClick={() => setIsEditing(true)}/>}
            </div>
            <div className="profile-header-content">
                {character?.profile && 
                    <div className="profile-header-info">
                        <Info isEditing={isEditing} profile={character?.profile} onAction={onInfoChange}/>
                    </div>
                }
                <div className="profile-header-nav">
                    <div className="profile-header-util">
                        <UtilBar/>
                    </div>
                    <div className="profile-header-resource">
                        <ResourceBar/>
                    </div>
                </div>
            </div>
            {character?.seals && (
                <div> <SealBox seals={character.seals}/> </div>
            )}

        </header>
        <section className="profile-status-attributes">
            {character?.basic && (
            <>
                <div>
                    <StatusBox 
                        onAction={onBasicChange} 
                        name="life" 
                        maxName="maxLife" 
                        max={character.basic.maxLife} 
                        atual={character.basic.life} 
                        title="VIDA" 
                        color="rgba(255, 0, 0, 0.45)"
                        disable={!isEditing}/>
                    <StatusBox 
                        name="magic" 
                        maxName="maxMagic" 
                        onAction={onBasicChange} 
                        max={character.basic.maxMagic} 
                        atual={character.basic.magic} 
                        title="MAGIA" 
                        color="rgba(17, 66, 241, 0.3)"
                        disable={!isEditing}/>
                    <StatusBox 
                        name="sanity" 
                        maxName="maxSanity"
                        onAction={onBasicChange} 
                        max={character.basic.maxSanity} 
                        atual={character.basic.sanity} 
                        title="SANIDADE" 
                        color="rgba(17, 66, 241, 0.5)"
                        disable={!isEditing}/>
                    <ExpositionBox 
                        onAction={onBasicChange}
                        awakening={character.basic.awakening} 
                        control={character.basic.control}
                        disable={!isEditing}/>
                </div>  
                <div className="profile-attributes-container">
                    <Attributes 
                        move={character.basic.movement} 
                        defense={character.basic.defense} 
                        vigor={character.basic.vigor}
                        onAction={onBasicChange}
                        disable={!isEditing}/>
                    <Individuality 
                        value={character.individuality.value} 
                        description={character.individuality.description}
                        onAction={onIndividualityChange}
                        disable={!isEditing}/>  
                </div> 
            </>)}
        </section>
        <section className="profile-extended-content">
            <Switch>
                <Route path="/player/history">
                    <TextBox description="A história do seu personagem" queryType="history"/>
                </Route>
                <Route path="/player/annotations">
                    <TextBox description="Suas anotações" queryType=""/>
                </Route>
                <Route path="/player/sheet">
                    <SheetPage/>
                </Route>
                <Route path="/player/spells">
                    <InventoryBase title="Seus feitiços e rituais"/>
                </Route>
                <Route path="/player/items">
                    <InventoryItem title="Seus itens"/>
                </Route>
            </Switch>
        </section>
    </section>
};

export default Profile