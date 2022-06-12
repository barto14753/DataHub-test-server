const SideBar = ({ setChosenDataset, datasetsNames }) => {
    const handleButtonClick = (name) => () => {
        setChosenDataset(name);
    }

    return (
        <div>
            <aside className="menu sidebar">
                <p className="menu-label">
                    Datasets
                </p>
                <ul className="menu-list">
                    {datasetsNames.map(dataset => {
                        return (
                            <li key={dataset} onClick={handleButtonClick(dataset)}>
                                <a> {dataset} </a>
                            </li>
                        );
                    })}
                </ul>
            </aside>
        </div>
    );
}

export default SideBar;
