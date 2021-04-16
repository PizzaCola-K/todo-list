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
    const clickHandler = () => {
        setColumnData((data)=>{
            const newData = [...data]
            newData.push({columnId: newData.length + 1, name:"적절한 기본이름", cards:[]})
            return newData
        })
    };

    return <Main clickHandler={clickHandler}>{columns}</Main>;
};

export default MainContainer;
