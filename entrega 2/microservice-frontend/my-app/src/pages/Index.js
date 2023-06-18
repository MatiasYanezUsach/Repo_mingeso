import { Col, Container, Row } from "react-bootstrap";
import mueble from '../mueble.jpg';

const Index = () => {
    return (
        <Container style={{display: 'flex', justifyContent: 'center', marginTop: '70px'}}>
            <Row>
                <Row className='text-center'>
                    <h1>Muebles STGO!</h1>
                </Row>
                <Row className='text-center'>
                    <img src={mueble} className="logo" alt="logo" media="(max-width: 400px)"/> 
                </Row>
            </Row>
        </Container>
    );
};

export default Index;