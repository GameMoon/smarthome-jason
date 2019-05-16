package SmartHomeEnv;
// Environment code for project smarthome.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import java.util.logging.*;


public class HouseEnv extends Environment {

    private Logger logger = Logger.getLogger("smarthome.mas2j."+HouseEnv.class.getName());
    private HouseModel model;
    private HouseView view;
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        model = new HouseModel(this);
        view = new HouseView(model);

        for(String roomName : model.getRoomNames()){
            createPerceptAgent("thisPlace("+roomName+")",roomName);
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        boolean viewUpdated = true;

        if(action.getFunctor().equals("lightsOn")){
            model.getRoom(action.getTerm(0).toString()).setLight(true);
            
        }
        else if(action.getFunctor().equals("lightsOff")){
            model.getRoom(action.getTerm(0).toString()).setLight(false);
        }
        else if(action.getFunctor().equals("extinguish")){
            model.getRoom(action.getTerm(0).toString()).extinguish(true);
        }
        else if(action.getFunctor().equals("extinguishOff")){
            model.getRoom(action.getTerm(0).toString()).extinguish(false);
        }
        else if(action.getFunctor().equals("run")){
            model.escape();
        }
        else{
            viewUpdated = false;
            logger.info("executing: " + action + ", but not implemented!");
        }

        if(viewUpdated) view.repaint();

        informAgsEnvironmentChanged();
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }

    public void createPerceptAgent(String literal,String agent){
        System.out.println("new percept agent: " + literal +" | "+ agent);
        addPercept(agent, Literal.parseLiteral(literal));
    }

    public void createPercept(String literal){
        System.out.println("new percept: "+literal);
        addPercept(Literal.parseLiteral(literal));
    }

    public void deletePercept(String literal){
        System.out.println("delete percept: "+literal);
        removePercept(Literal.parseLiteral(literal));
    }
    public void deletePerceptAgent(String literal,String agent){
        System.out.println("delete percept agent: " + literal+ " | "+ agent);
        removePercept(agent,Literal.parseLiteral(literal));
    }
}
