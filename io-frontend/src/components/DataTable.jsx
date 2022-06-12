import React from 'react';
import moment from 'moment';

const DataTable = ({plot}) => {
    const mapRecord = (record) => { 
        const [dataName, dataValue] = Object.entries(record).find(([key, val]) => key !== 'x')

        return (

            <tbody key={dataName + record.x}>

                <tr>
                    <td>
                        <abbr title="Date&Time">
                            {moment(record.x).format('DD.MM.YYYY HH:mm')}
                        </abbr>
                    </td>
                    {(plot.unit === undefined) ?
                        <td>
                            <abbr title="Value">
                                {(record.tag === "OUTLIER")?
                                    <>
                                        <div className="has-text-danger">{(parseFloat(dataValue).toFixed(2))}</div>
                                    </>

                                    :
                                    <>

                                        <div>{(parseFloat(dataValue).toFixed(2))}</div>
                                    </>

                                }

                            </abbr>
                        </td>
                        :   
                        <td>

                            <abbr title="Value">
                                {(record.tag === "OUTLIER")?
                                    <div className="has-text-danger">{(parseFloat(dataValue).toFixed(2) + " " + plot.unit)}</div>
                                    :
                                    <div>{(parseFloat(dataValue).toFixed(2) + " " + plot.unit)}</div>
                                }

                            </abbr>
                        </td>
                    }
                    <td>
                        <abbr title="Name">
                            {dataName}                            
                        </abbr>
                    </td>
                </tr>
            </tbody>
        );
    };

    return (
        <section className="column is-3 table-container">
            <table className="table is-bordered">
                <thead>
                    <tr>
                        <th><abbr title="Date&Time">Date</abbr></th>
                        <th><abbr title="Value">Value</abbr></th>
                        <th><abbr title="Name">Name </abbr></th>
                    </tr>
                </thead>
                {plot.records.map(mapRecord)}
            </table>
        </section>
    );
};

export default DataTable;