import React from 'react';
import chartsIcon from './charts_icon.png';

const Headbar = ({navigate}) => (
    <nav className="navbar is-black is-fixed-top" role="navigation" aria-label="main navigation">
        <div className="navbar-brand">
            <a className="navbar-item navbar-title">
                <img src={chartsIcon} alt="" width="32" height="32" />
                DATAHUB GRAPHER
            </a>
            <a role="button" className="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>
        <div id="navbarBasicExample" className="navbar-menu">
            <div className="navbar-start">
                <a className="navbar-item" onClick={() => navigate('loadConfig')}>
                    Load Config
                </a>
                <a className="navbar-item" onClick={() => navigate('charts')} >
                    Charts
                </a>
                <a className="navbar-item" href="http://localhost:3000/resources/how_to_.pdf" target="_blank">
                    Documentation
                </a>
            </div>
        </div>
    </nav>
)

export default Headbar;
