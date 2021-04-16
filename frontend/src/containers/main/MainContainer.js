import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ColumnContainer from './column/ColumnContainer';
import Main from '../../components/main/Main';


//prettier-ignore
const MainContainer = () => {
    const [columnData, setColumnData] = useState([]);
    const [columns, setColumns] = useState();

    useEffect(() => axios.get('/api/columns').then((json) => setColumnData(() => json.data.columns)), []);

    useEffect(() => {
        setColumns(() =>
            columnData.map(({ columnId, name, cards }, i) => (
                <li key={i}><ColumnContainer columnId={columnId} title={name} list={cards} setColumnData={setColumnData}/></li>
            )),
        );
    }, [columnData]);


    // 컬럼 추가 버튼 이벤트 (클릭)
    const clickHandler = async () => {
        const json = await axios.post(`api/columns`, {column:{name:"New Column"}} )
        setColumnData((data)=>{
            const newData = [...data]
            newData.push(json.data.column)
            return newData
        })
    };

    return <Main clickHandler={clickHandler}>{columns}</Main>;
};

export default MainContainer;
