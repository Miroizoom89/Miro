import React, {Component} from "react";
import map from "./img/zoomap.jpg"
import mapA from "./img/zoomapAA.jpg"
import mapB from "./img/zoomapBB.jpg"
import mapC from "./img/zoomapCC.jpg"
import mapD from "./img/zoomapDD.jpg"
import mapE from "./img/zoomapEE.jpg"
import mapF from "./img/zoomapFF.jpg"

class Map extends Component {

    //selects map depending on first letter of currently chosen species' cage
    selectMap() {
        if (this.props.id === 0) {
            return <img src={map} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "A") {
            return <img src={mapA} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "B") {
            return <img src={mapB} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "C") {
            return <img src={mapC} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "D") {
            return <img src={mapD} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "E") {
            return <img src={mapE} alt=""/>
        } else if (this.props.specie.cage.substring(0, 1) === "F") {
            return <img src={mapF} alt=""/>
        }
    }

    render() {
        return (
            <div id="map">
                {this.selectMap()}
            </div>
        );
    }
}

export default Map