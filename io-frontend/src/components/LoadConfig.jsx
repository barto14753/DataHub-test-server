import React, {useState} from 'react';
import "./LoadConfig.css";
import { toast } from 'react-toastify';
import HashLoader from 'react-spinners/HashLoader';

const fetchConfig = (config) => fetch('http://localhost:8080/config/load', {
    body: config,
    method: 'post',
    headers: { 'Content-Type': 'application/json'},
}).then(res => res.json());


const LoadConfigPage = ({ onConfigUploaded }) => {
    const [isLoading, setIsLoading] = useState(false);

    const handleFileUpload = (event) => {
        setIsLoading(true);
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = async e => {
            try {
                const config = e.target.result;
                const response = await fetchConfig(config);
                if (response.error || !response.datasets) {
                    throw Error(response.error);
                }
                onConfigUploaded(response.datasets);
            } catch (e) {
                console.log('err', e);
                toast.error(`Config upload errors. Please check your configuration file.`);
            }
            setIsLoading(false);
            event.target.value = null;
        };
        reader.readAsText(file);
    }

    return (
        <div className="hero is-fullheight-with-navbar load-config-container">
            <div className="box file-upload-box">
                <div className="file-upload-header">
                    <div className="file-upload-title">
                        <h2>Upload configuration file </h2>
                    </div>
                    <div className="file-upload-subtitle">
                        Let's get started with plotting graphs! First upload the configuration file in json format.
                        If you have not received any configuration file please contact with system administrator.
                        Remember that in order to download data, vpn connection with agh should already be established.
                    </div>
                </div>
                <div className="file is-boxed is-large is-centered file-upload-container">
                    <label className={`file-label ${isLoading ? 'removed' : ''}`}>
                        <span className="file-cta">
                            <span className="file-icon">
                                <i className="fas fa-upload"></i>
                            </span>
                            <span className="file-label">
                                choose a file… 
                            </span>
                            <span className="file-label file-upload-sublabel">
                                or drag and drop inside
                            </span>
                        </span>
                        <input className="file-input" type="file" name="resume" onChange={handleFileUpload} accept=".json"/>
                    </label>
                    <HashLoader color="#AA450B" loading={isLoading} size={85}/>
                </div>
            </div>
        </div>
    );
}

export default LoadConfigPage;
