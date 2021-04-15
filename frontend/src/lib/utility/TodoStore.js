import React, { createContext, useState, useMemo } from 'react';

export const TodoContext = createContext({
    // Context의 기본값 지정
    isDataUpdate: null,
    setIsDataUpdate: () => {},
});

const TodoStore = ({ children }) => {
    const [isDataUpdate, setIsDataUpdate] = useState(false);
    // value 객체는 객체이므로 리렌더링의 원인이 됨, useMemo로 캐싱
    const value = useMemo(() => ({ isDataUpdate, setIsDataUpdate }), [
        isDataUpdate,
        setIsDataUpdate,
    ]);
    return (
        <TodoContext.Provider value={value}>{children}</TodoContext.Provider>
    );
};

export default TodoStore;
