import React from 'react';
import styled from 'styled-components';
import { TranslateBtn } from '../../common/CommonStyledComponent';

const StyledColumnDeleteButton = styled(TranslateBtn)`
    svg {
        fill: #bdbdbd;
        &:hover {
            fill: #ff4343;
        }
    }
`;

const ColumnDeleteButton = ({ onClick, onMouseEnter, onMouseLeave }) => {
    return (
        <StyledColumnDeleteButton
            onClick={onClick}
            onMouseEnter={onMouseEnter}
            onMouseLeave={onMouseLeave}
        >
            <svg
                width="12"
                height="12"
                viewBox="0 0 12 12"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
            >
                <path d="M1.5 11.25L0.75 10.5L5.25 6L0.75 1.5L1.5 0.75L6 5.25L10.5 0.75L11.25 1.5L6.75 6L11.25 10.5L10.5 11.25L6 6.75L1.5 11.25Z" />
            </svg>
        </StyledColumnDeleteButton>
    );
};

export default ColumnDeleteButton;
