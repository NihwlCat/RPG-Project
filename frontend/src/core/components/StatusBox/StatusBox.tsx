import React from 'react'
import './StatusBox.scss'

type Props = {
    title: string;
    color: string;
    max: number;
    atual: number;
    maxName: string;
    name: string;
    onAction: (event: React.ChangeEvent<HTMLInputElement>) => void;
    disable: boolean;
}

const StatusBox = ({ title, color, max, atual, maxName, name, onAction, disable}:Props) => {
    const percentage = Math.round((atual / max) * 100)

    return <div className="status-box-container">
        <div className="status-info-container">
            <span>{title}</span>
            <div className="status-value">
                <input type="number" disabled={disable} value={atual} onChange={event => onAction(event)} name={name}/>
                <input type="number" disabled={disable} value={max} onChange={event => onAction(event)} name={maxName}/>
            </div>
        </div>
        <div className="status-progress-bar">
            <div style={{width: `${percentage}%`, backgroundColor: `${color}`}}></div>
        </div>
    </div>
}

export default StatusBox;


