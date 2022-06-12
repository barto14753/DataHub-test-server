import 'bulma/css/bulma.min.css';
import { useCallback, useEffect, useRef, useState } from 'react'
import Headbar from './components/Headbar';
import LoadConfigPage from './components/LoadConfig';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ChartsPage from './components/ChartsPage';


function App() {
    const [datasets, setDatasets] = useState([]);
    const [openedPage, setOpenedPage] = useState('loadConfig');
    const updateIntervals = useRef([]);
    
    const fetchAllDatasets = useCallback(
        async () => {
            try {
                const response = await fetch('http://localhost:8080/datasets/all', {
                    method: 'get',
                }).then(res => res.json());
                setDatasets(response);
                updateIntervals.current.forEach(clearInterval);
                updateIntervals.current = response.map(dataset => (
                    setInterval(() => updateDataset(dataset.name), dataset.updates.update_interval_sec * 1000)
                ));
            } catch (e) {
                console.log('Datasets fetch error', e);
                toast.error('Failed to fetch datasets');
            }
        },
        []
    );

    const updateDataset = async (datasetName) => {
        try {
            const response = await fetch(`http://localhost:8080/datasets/${datasetName}`, {
                method: 'get',
            }).then(res => res.json());
            setDatasets(oldDatasets => {
                const datasetIndex = oldDatasets.findIndex(d => d.name === datasetName);
                const updatedDatasets = [...oldDatasets];
                updatedDatasets[datasetIndex] = response;
                return updatedDatasets;
            });
        } catch (e) {
            console.log(`Dataset ${datasetName} fetch error`, e);
            toast.error(`Failed to fetch dataset ${datasetName}`);
        }
    };

    useEffect(() => {
        fetchAllDatasets();
    }, [fetchAllDatasets]);
    

    const handleConfigUploaded = () => {
        setOpenedPage('charts');
        fetchAllDatasets();
    };
    

    const pages = {
        loadConfig: <LoadConfigPage onConfigUploaded={handleConfigUploaded} />,
        charts: <ChartsPage datasets={datasets} updateDataset={updateDataset} />
    };
    
    return (
        <div>
            <Headbar navigate={setOpenedPage} />
            {pages[openedPage]}
            <ToastContainer
                autoClose={2000}
            />
        </div>
    );
}

export default App;
