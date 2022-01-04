import React, {Component} from "react";
import SpeciesList from "./SpeciesList";
import Intro from "./Intro"
import Map from "./Map"
import "./Client.css"

class Client extends Component {

    /* STATES
    * species: List of every species
    * disSpecie: One, currently chosen species
    * disID: ID of currently chosen species
     */

    state = {
        species: [],
        disSpecie: null,
        disID: 0
    }

    // Downloads every species
    componentDidMount() {
        fetch("http://localhost:8080/api/v1/species/list")
            .then(response => response.json())
            .then(species => {
                    console.log(species);
                    this.setState({species})
                }
            );
    }

    // SpeciesList.js uses it to inform Client.js which species is chosen
    justClicked(id) {
        // If the same species is clicked then page returns to default content
        if (id === this.state.disID) {
            this.setState({disID: 0})
        } else {
            const index = this.state.species.findIndex(e => e.id === id);
            this.setState({disSpecie: this.state.species[index]});
            this.setState({disID: id})
        }
    }

    render() {
        return (
            <div className="Torso">

                {/*Some info about the ZOO or info about chosen species*/}
                <Intro
                    id={this.state.disID}
                    specie={this.state.disSpecie}
                />

                {/*Creates clickable list of species*/}
                <div id="specieslist">
                    {this.state.species.map(specie => <SpeciesList
                            name={specie.species}
                            id={specie.id}
                            disID={this.state.disID}
                            justClicked={this.justClicked.bind(this)}
                        />
                    )}
                </div>

                {/*Map of the ZOO*/}
                <Map
                    id={this.state.disID}
                    specie={this.state.disSpecie}
                />

                <div style={{clear: "both"}}/>

            </div>
        );
    }
}

export default Client;
