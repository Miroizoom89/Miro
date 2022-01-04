import React, {Component} from "react";

class TasksList extends Component {

    // Converts time given in HH:MM:SS form to miliseconds
    hhmmssToMs(hhmmss) {
        const a = hhmmss.split(':')
        return (+a[0]) * 3600000 + (+a[1]) * 60000 + (+a[2]) * 1000
    }

    // Returns color corresponding to remaining time
    color() {
        const now = Date.now()
        const end = this.hhmmssToMs(this.props.task.duration) + (Date.parse(this.props.task.startTime))
        const remainingTime = end - now
        if (remainingTime < 0) {
            return "#FF0000"
        } else if (remainingTime < 3600000) {
            return "#FF8C00"
        } else if (remainingTime < 7200000) {
            return "#FFD700"
        } else {
            return "#ADFF2F"
        }
    }

    // Renders one task tile
    render() {
        return (
            <div className={`task ${this.props.IDChosen === this.props.task.id ? 'clicked' : ''}`}
                 onClick={() => this.props.justClicked(this.props.task)} style={{backgroundColor: `${this.color()}`}}>
                <span style={{marginLeft: 6, float: "left", width: 25}}>
                    #{this.props.task.id}
                </span>
                <span style={{marginLeft: 80, float: "left"}}>
                    {this.props.task.startTime.substring(0, 10) + " " + this.props.task.startTime.substring(11, 16)}
                </span>
                <span style={{marginLeft: 60, float: "left"}}>
                    {this.props.task.duration.substring(0, 5)}
                </span>
                <div style={{clear: "both"}}/>
                <p style={{padding: 10, fontSize: 15, margin: 0}}>
                    {this.props.task.description.substring(0, 150)}
                </p>
            </div>
        );
    }
}

export default TasksList