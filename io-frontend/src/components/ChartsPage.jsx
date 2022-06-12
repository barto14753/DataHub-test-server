import React, { useState } from 'react';
import moment from 'moment';
import Sidebar from './Sidebar';
import Chart from './Chart';
import DataTable from './DataTable';
import "./Styles.css";
import StaticData from './StaticData';

const ChartsPage = ({datasets, updateDataset}) => {
    const [chosenDataset, setChosenDataset] = useState('');
    const datasetsNames = datasets.map(d => d.name);

    const onDatasetChosen = async (datasetName) => {
        setChosenDataset(datasetName);
        updateDataset(datasetName);
    }

    let startingInfobox = <div className="infobox-container">
        <div className="infobox">
            <div className="infobox-title">
                Welcome
            </div>
            Welcome to datahub grapher application. 
            You can now start exploring available dataset.
            Select dataset to visualize by clicking on buttons on the left side of the screen.
            Plotted sensors data should appear soon after.  
        </div>
    </div>

    if (chosenDataset) {
        startingInfobox = null;
    }
    const mapPlot = (plot) => {
        return (
            <header className="columns" key={plot.name}>
                <Chart plot={plot} />
                <DataTable plot={plot} />
            </header>
        )
    };

    const mapDataset = (dataset) => {
        if (chosenDataset !== dataset.name) {
            return null;
        }

        const plots = dataset.plots.map(({plot_name, plot_data }) => {
            const plot = {
                name: plot_name,
                records: [],
                lines: {}
            };
            plot_data.forEach(({data_name, plot_color}) => {
                const sensor_data = dataset.sensors_data.find(d => d.name === data_name);
                sensor_data.records.forEach(r => {
                    plot.records.push({
                        [data_name]: r.data,
                        x: moment(r.timestamp).valueOf(),
                        tag: r.tag,
                    })
                })
                plot.lines[data_name] = plot_color
            })
            return plot;
        });


        return ( 
            <div key={dataset.name}>
                <StaticData staticData={dataset.static_data}/>
                {plots.map(mapPlot)}
            </div>
        ); 
    };

    return (
        <div className="columns">
            {startingInfobox}
            <div className="column is-2 buttons-container">
                <Sidebar setChosenDataset={onDatasetChosen} datasetsNames={datasetsNames}/>
            </div>
            <div className="column">
                <header>
                    {datasets.map(mapDataset)}
                </header>
            </div>
        </div>
    );
};

export default ChartsPage;
