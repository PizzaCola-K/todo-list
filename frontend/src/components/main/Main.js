import React from "react";
import styled from "styled-components";
import { TranslateBtn } from "../common/CommonStyledComponent";

const StyledMainUl = styled.ul`
    display: flex;
    padding: 0 50px;
`;

const ColumnAddButton = styled(TranslateBtn)`
    background-color: #00529b;
    color: #fff;
    font-size: 32px;

    display: flex;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    right: 65px;
    margin-top: 24px;

    justify-content: center;
    align-items: center;
    position: absolute;

    &:hover {
        background-color: #86c6ff;
    }
`;

const Main = ({children, clickHandler}) => {
    return (
        <>
            <StyledMainUl>{children}</StyledMainUl>
            <ColumnAddButton onClick={clickHandler}>+</ColumnAddButton>
        </>
    )
};

export default Main;