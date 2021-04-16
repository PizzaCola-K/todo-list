import React, { useEffect, useState } from 'react';
import Column from '../../../components/main/column/Column';
import CardContainer from "./card/CardContainer";
import CardInputContainer from "./card/CardInputContainer";

//prettier-ignore
const ColumnContainer = ({ columnId, title, list, setColumnData }) => {
    const [cardList, setCardList] = useState(list);
    const [renderedList, setRenderedList] = useState();
    const [isEditNow, setIsEditNow] = useState(false);
    const [inputTitle, setInputTitle] = useState(title);

    useEffect(() => setCardList(() => list), [list])

    // eslint-disable-next-line react-hooks/exhaustive-deps
    useEffect(() => setRenderedList(() => cardList.map(render)), [cardList]);

    const render = (v, index) => {
        v = {...v, index, setColumnData}
        return v.isInput
            ? <li key={index}><CardInputContainer {...v} /></li>
            : <li key={index}><CardContainer {...v} /></li>
    };

    // const plusEvent = () => setCardList(() => [{ isInput: true, title: '', body: '', columnId:columnId }, ...cardList,]);
    const plusEvent = () => {
        setColumnData((data)=>{
            const newData = [...data]
            newData[columnId-1].cards = [{ isInput: true, title: '', body: '', columnId:columnId },...newData[columnId-1].cards]
            return newData
        })
    };

    const deleteEvent = () => {
        setColumnData((data)=>[...data].filter((card)=>card.columnId!==columnId));
    };

    const dbEvent = () => {
        setIsEditNow(true);
    };

    const editInputEnterEvent = ({target, keyCode}) => {
        if (keyCode !== 13) return;
        const newName =  target.value;
        setColumnData((data)=>data.map((column)=> column.columnId !== columnId ? column : {columnId, name:newName, cards:list}))
        setIsEditNow(false);
    };

    const changeTitle = ({ target }) => setInputTitle(() => target.value);


    const onEvents = { plusEvent, deleteEvent, dbEvent, editInputEnterEvent, changeTitle };

    return (
        <Column title={inputTitle} cardList={renderedList} isEditNow={isEditNow} onEvents={onEvents} />
    );
};

export default ColumnContainer;
