import { Spell } from 'core/utils/types';
import './SealBox.scss'

type Props = {
    seals: Spell[];
}

const SealBox = ({seals}: Props) => {

    return <div className="seals-box">
        <div className="seals-title">
            <span>SIGILOS</span>
        </div>
        <div className="seals-content">
            {seals && seals.map(seal => (
                <div className="seals-item">
                    <img src={seal.imgUrl} title={seal.description} alt={seal.name}/>
                </div>
            ))}
        </div>
    </div>

}

export default SealBox;