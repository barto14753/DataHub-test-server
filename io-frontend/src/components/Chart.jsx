import React from 'react';
import moment from 'moment';
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Label, Legend } from 'recharts';

const Chart = ({plot}) => {
    const lines = Object.entries(plot.lines).map(([dataName, color]) => {
        return <Line type="monotone" key={dataName} dataKey={dataName} stroke={color} />
    })
    const max = Math.max.apply(Math, plot.records.map(r => r[Object.keys(r).find(k => k !== 'x')]));
    const min = Math.min.apply(Math, plot.records.map(r => r[Object.keys(r).find(k => k !== 'x')]));
    const distance = max - min;

    return (
        <div className='chart-box'>
            <LineChart
                width={800}
                height={500}
                data={plot.records}
                margin={{left: 60, top: 20}}
                normalized
            >
                <Legend verticalAlign="top" />
                {lines}
                <CartesianGrid stroke="#ccc"/>
                <XAxis
                    dataKey="x"
                    type="number"
                    domain={[plot.records[0]?.x || 0, plot.records[plot.records.length - 1]?.x || 0]}
                    tickFormatter={unixTimestamp => moment(unixTimestamp).format('DD.MM.YYYY')}
                    padding={{left: 10}}
                />
                <YAxis
                    domain={[min - 0.15 * distance, max + 0.15 * distance]}
                    tickFormatter={val => Math.round(val)}
                    padding={{bottom: 15}}
                    mirror={true}
                >
                    <Label
                        position="left"
                        angle={270}
                        style={{textAnchor: "middle"}}
                        offset={40}
                        fontSize={26}
                        value={plot.name}
                    />
                </YAxis>
                <Tooltip
                    formatter={(value, name, props) => ([Math.round(value), plot.name])}
                    labelFormatter={unixTimestamp => moment(unixTimestamp).format('DD.MM.YYYY HH:mm')}
                />
            </LineChart>
        </div>
    )
}

export default Chart;