import React from 'react';
import './App.css';
import TopbarContainer from './containers/topbar/TopbarContainer';
import MainContainer from './containers/main/MainContainer';
import TodoStore from "./lib/utility/TodoStore";

const App = () => {
    return (
        <TodoStore>
            <TopbarContainer />
            <MainContainer />
        </TodoStore>
    );
};

export default App;
