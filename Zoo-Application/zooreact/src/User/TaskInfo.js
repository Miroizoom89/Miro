import React, {Component} from "react";

class TaskInfo extends Component {

    // Converts time given in HH:MM:SS format to miliseconds
    hhmmssToMs(hhmmss) {
        const a = hhmmss.split(':')
        return (+a[0]) * 3600000 + (+a[1]) * 60000 + (+a[2]) * 1000
    }

    // Converts time given in miliseconds to HH:MM:SS format
    msToHMS(ms) {
        let seconds = (Math.abs(ms)).toString().substring(0, (Math.abs(ms).toString().length - 3))
        let hours = Math.floor(seconds / 3600)
        seconds = seconds % 3600
        let minutes = Math.floor(seconds / 60)
        if (minutes < 10) {
            minutes = "0" + minutes
        }
        seconds = seconds % 60
        if (seconds < 10) {
            seconds = "0" + seconds
        }
        if (ms >= 0) {
            return (hours + ":" + minutes + ":" + seconds)
        } else {
            return ("-" + hours + ":" + minutes + ":" + seconds)
        }
    }

    // Calculates remaining time of the chosen task
    remainingTime() {
        const end = this.hhmmssToMs(this.props.task.duration) + (Date.parse(this.props.task.startTime))
        const now = this.props.date
        return this.msToHMS(end - now)
    }

    // Displays info of the chosen task in special window
    content() {
        if (this.props.id) {
            return (
                <div id="taskInfo">
                    <div style={{padding: 10, textAlign: "center", fontSize: 30}}>
                        <b>Informacje o zadaniu #{this.props.task.id}</b>
                    </div>
                    <div className="info">
                        <b>Wybieg:</b> {this.props.task.cage}
                    </div>
                    <div className="info">
                        <b>Gatunek:</b> {this.props.task.species.species}
                    </div>
                    <div className="info">
                        <b>Początek
                            zadania:</b> {this.props.task.startTime.substring(0, 10) + " " + this.props.task.startTime.substring(11, 16)}
                    </div>
                    <div className="info">
                        <b>Spodziewany czas trwania:</b> {this.props.task.duration.substring(0, 5)}
                    </div>
                    <div className="info" style={{minHeight: 230}}>
                        <b>Opis zadania:</b>
                        <br/>
                        <span style={{fontSize: 18}}>
                            {this.props.task.description}
                        </span>
                    </div>
                    <div style={{paddingLeft: 30, fontSize: 27, float: "left"}}>
                        <b>Pozostały czas: {this.remainingTime()}</b>
                    </div>
                    <input type="button" value="Zakończono" className="confirm" onClick={() => this.props.finished()}/>
                    <div style={{clear: "both"}}/>
                </div>
            )
        } else {
            return (
                <div>
                </div>
            )
        }
    }

    render() {
        return (
            this.content()
        );
    }
}

export default TaskInfo