import React, {Component} from "react";

class Intro extends Component {

    showContent = () => {
        if (this.props.id === 0) {
            return (
                <div>
                    <span style={{fontWeight: 700, fontSize: 60}}> Witamy w naszym ZOO!!!</span>
                    <span style={{textAlign: "justify"}}>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                            Mauris a elit in mauris accumsan
                            consequat.
                            Aliquam et elit faucibus, sodales nulla at, suscipit nunc. Sed ac mi nibh. Nunc sed tempus
                            massa.
                            Vivamus volutpat aliquet sem in euismod. In hac habitasse platea dictumst. Donec nec mauris
                            posuere,
                            rhoncus sem eu, finibus sem. Curabitur convallis velit eget nunc fermentum, nec tincidunt
                            arcu fermentum. Integer vulputate quis leo in congue.</p>

                        <p>Ut consequat finibus diam. Suspendisse eget diam id lorem pellentesque vestibulum. Quisque
                            scelerisque consequat aliquam. Mauris non lacus et lorem imperdiet tincidunt.
                            Proin et eros aliquam, tincidunt dolor et, dapibus lectus. Mauris pretium, lorem nec
                            sollicitudin
                            vulputate, ex ligula rutrum urna, sed egestas nisl eros dictum nisl. Integer augue leo,
                            tincidunt
                            vitae iaculis ac, mollis eget sem. Cras faucibus maximus dolor, eget porttitor urna euismod eu.
                            Nullam molestie velit velit, vitae ullamcorper diam porta dictum. Nam vulputate leo eget neque
                            mattis, ut vulputate leo fringilla. Vivamus euismod, ex et blandit pulvinar, lacus mi vestibulum
                            massa, nec aliquet libero nunc ac neque. Class aptent taciti sociosqu ad litora torquent per
                            conubia
                            nostra, per inceptos himenaeos. Integer eget tincidunt ante. Donec in hendrerit mauris.
                            Pellentesque
                            pulvinar ultricies dui, vitae varius ex fringilla et.</p>
                    </span>
                </div>
            )
        } else {
            return (
                <div>
                    <span style={{
                        fontWeight: 700,
                        fontSize: 60,
                        textAlign: "justify"
                    }}>{this.props.specie.species.substring(0, 1).toUpperCase() + this.props.specie.species.substring(1)}</span>
                    <p>{this.props.specie.description}</p>
                    <p>Pora karmienia : {this.props.specie.feedingHour.substring(0,5)}</p>
                </div>
            )
        }
    }

    render() {
        return (
            <div id="intro">
                {this.showContent()}
            </div>
        );
    }
}

export default Intro;