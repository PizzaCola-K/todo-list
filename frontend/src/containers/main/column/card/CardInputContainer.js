import React, { useState, useContext } from 'react';
import axios from 'axios';
import CardInput from "../../../../components/main/column/card/CardInput";
import { TodoContext } from "../../../../lib/utility/TodoStore";

const CardInputContainer = ({ title, body, index, setColumnData, columnId, cardId, previousCardId }) => {
    const { setIsDataUpdate } = useContext(TodoContext);
    const [inputTitle, setTitle] = useState(title);
    const [inputBody, setBody] = useState(body);
    const isAble = inputTitle.length * inputBody.length;

    const addCard = async () => {
        if (!isAble) return;
        const json = title.length * body.length
            ? await axios.put(`api/columns/${columnId}/cards/${cardId}`, {card: { title: inputTitle, body:inputBody, columnId, cardId, previousCardId}})
            : await axios.post(`api/columns/${columnId}/cards/`, {card: { title: inputTitle, body:inputBody}})

        setColumnData((data)=>{
            const newData = [...data]
            const columnIndex = newData.findIndex((v)=>v.columnId===columnId)
            const left = newData[columnIndex].cards.slice(0, index);
            const right = newData[columnIndex].cards.slice(index + 1);
            newData[columnIndex].cards = left.concat(json.data.card, right);
            return newData
        });
        setIsDataUpdate(true);
    };

    const deleteCard = () => {
        title.length * body.length
            ? setColumnData((data)=>{
                const newData = [...data]
                const columnIndex = newData.findIndex((v)=>v.columnId===columnId)
                const left = newData[columnIndex].cards.slice(0, index);
                const right = newData[columnIndex].cards.slice(index + 1);
                newData[columnIndex].cards = left.concat({ title, body, previousCardId, cardId, columnId }, right);
                return newData
            })
            : setColumnData((data)=>{
                const newData = [...data]
                const columnIndex = newData.findIndex((v)=>v.columnId===columnId)
                newData[columnIndex].cards = newData[columnIndex].cards.filter((_, i) => i !== index)
                return newData
            })
    };
    const changeTitle = ({ target }) => setTitle(() => target.value);
    const changeBody = ({ target }) => setBody(() => target.value);

    const data = { title, body, inputTitle, inputBody, isAble };
    const onEvents = {
        addCard, deleteCard, changeTitle, changeBody
    };

    return (<CardInput data={data} onEvents={onEvents} />)
};

export default CardInputContainer;