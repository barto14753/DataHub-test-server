import React from 'react';
import moment from 'moment';
const StaticData = ({staticData}) => {
    return (
        <div className="static-data-container">
            <div className="static-data-infobox">
                <div className="static-data-location has-text-primary">
                    {staticData.find(s => s.name === 'Location')?.records[0]?.data || null}
                </div>
                <div className="static-data-ip has-text-centered">
                    {((staticData.find(s => ['Network Address', 'IP'].includes(s.name))?.records[0]?.data || null) !== null)?
                       <>Source IP: {staticData.find(s => ['Network Address', 'IP'].includes(s.name))?.records[0]?.data || null}</>
                        :
                        <></>
                    }

                </div>
                <div className="static-data-time has-text-centered">
                    {(moment(staticData.find(s => s.name === 'Location')?.records[0]?.timestamp).format('DD.MM.YYYY HH:mm') || null)?
                    <>Collected data til: {moment(staticData.find(s => s.name === 'Location')?.records[0]?.timestamp).format('DD.MM.YYYY HH:mm') || null}</>
                        :
                        <></>
                    }
                </div>
            </div>
        </div>
    );
}

export default StaticData;
