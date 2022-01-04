import React, {Component} from "react";
import Table from 'react-bootstrap/Table';

class Tablebox extends Component {

    // This method is responsible for displaying correct Table headings depending on chosen category
    attributes() {
        if (this.props.category === "species") {
            return (
                <tr>
                    <th>#</th>
                    <th>nazwa</th>
                    <th>wybieg</th>
                    <th>opis</th>
                    <th>jedzenie</th>
                    <th><input type="button" className="add" value="dodaj"
                               onClick={() => this.props.sendToAdmin(null, "add")}/></th>
                </tr>
            )
        } else if (this.props.category === "animals") {
            return (
                <tr>
                    <th>#</th>
                    <th>imię</th>
                    <th>gatunek</th>
                    <th>data urodzenia</th>
                    <th><input type="button" className="add" value="dodaj"
                               onClick={() => this.props.sendToAdmin(null, "add")}/></th>
                </tr>
            )
        } else if (this.props.category === "users") {
            return (
                <tr>
                    <th>#</th>
                    <th>login</th>
                    <th>stanowisko</th>
                    <th><input type="button" className="add" value="dodaj"
                               onClick={() => this.props.sendToAdmin(null, "add")}/></th>
                </tr>
            )
        } else if (this.props.category === "tasks") {
            return (
                <tr>
                    <th>#</th>
                    <th>login</th>
                    <th>wybieg</th>
                    <th>treść</th>
                    <th>początek</th>
                    <th>czas trwania</th>
                    <th><input type="button" className="add" value="dodaj"
                               onClick={() => this.props.sendToAdmin(null, "add")}/></th>
                </tr>
            )
        } else if (this.props.category === "zookeepers") {
            return (
                <tr>
                    <th>#</th>
                    <th>imię</th>
                    <th>nazwisko</th>
                    <th>login</th>
                    <th>rola</th>
                    <th><input type="button" className="add" value="dodaj"
                               onClick={() => this.props.sendToAdmin(null, "add")}/></th>
                </tr>
            )
        }
    }

    // This method is responsible for displaying list of objects in Table
    objects() {
        if (this.props.category === "species") {
            return (
                <tbody>
                {this.props.list.map(object => <tr>
                    <td>{object.id}</td>
                    <td>{object.species}</td>
                    <td>{object.cage}</td>
                    <td>{object.description.substring(0, 40)}</td>
                    <td>{object.feedingHour.substring(0, 5)}</td>
                    <td><input type="button" value="usuń" className="delete"
                               onClick={() => this.props.sendToAdmin(object, "delete")}/>
                        <input type="button" value="edytuj" className="edit"
                               onClick={() => this.props.sendToAdmin(object, "update")}/></td>
                </tr>)}
                </tbody>
            )
        } else if (this.props.category === "animals") {
            return (
                <tbody>
                {this.props.list.map(object => <tr>
                    <td>{object.id}</td>
                    <td>{object.name}</td>
                    <td>{object.species}</td>
                    <td>{object.dob}</td>
                    <td><input type="button" value="usuń" className="delete"
                               onClick={() => this.props.sendToAdmin(object, "delete")}/>
                        <input type="button" value="edytuj" className="edit"
                               onClick={() => this.props.sendToAdmin(object, "update")}/></td>
                </tr>)}
                </tbody>
            )
        } else if (this.props.category === "users") {
            return (
                <tbody>
                {this.props.list.map(object => <tr>
                    <td>{object.id}</td>
                    <td>{object.login}</td>
                    <td>{object.role}</td>
                    <td><input type="button" value="usuń" className="delete"
                               onClick={() => this.props.sendToAdmin(object, "delete")}/>
                        <input type="button" value="edytuj" className="edit"
                               onClick={() => this.props.sendToAdmin(object, "update")}/></td>
                </tr>)}
                </tbody>
            )
        } else if (this.props.category === "tasks") {
            return (
                <tbody>
                {this.props.list.map(object => <tr>
                    <td>{object.id}</td>
                    <td>{object.login}</td>
                    <td>{object.cage}</td>
                    <td>{object.description.substring(0, 25)}</td>
                    <td>{object.startTime.substring(0, 10) + " " + object.startTime.substring(11, 16)}</td>
                    <td>{object.duration.substring(0, 5)}</td>
                    <td><input type="button" value="usuń" className="delete"
                               onClick={() => this.props.sendToAdmin(object, "delete")}/>
                        <input type="button" value="edytuj" className="edit"
                               onClick={() => this.props.sendToAdmin(object, "update")}/></td>
                </tr>)}
                </tbody>
            )
        } else if (this.props.category === "zookeepers") {
            return (
                <tbody>
                {this.props.list.map(object => <tr>
                    <td>{object["first"].id}</td>
                    <td>{object["first"].name}</td>
                    <td>{object["first"].surname}</td>
                    <td>{object["first"].login}</td>
                    <td>{object["second"]}</td>
                    <td><input type="button" value="edytuj" className="edit"
                               onClick={() => this.props.sendToAdmin(object, "update")}/></td>
                </tr>)}
                </tbody>
            )
        }
    }

    render() {
        return (
            <div id="tablebox">
                <Table striped bordered hover size="sm" className="Tabela">
                    <style scoped>
                        @import 'bootstrap/dist/css/bootstrap.min.css';
                    </style>
                    <thead>
                    {this.attributes()}
                    </thead>
                    {this.objects()}
                </Table>
            </div>
        );
    }
}

export default Tablebox