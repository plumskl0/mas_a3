package mas_a3;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoObserver extends BaseObserver{

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> createUserTurtles(int number, Closure closure) {
		AgentSet<mas_a3.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof mas_a3.relogo.UserTurtle){
				result.add((mas_a3.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> createUserTurtles(int number) {
		return createUserTurtles(number,null);
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> createOrderedUserTurtles(int number, Closure closure) {
		AgentSet<mas_a3.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof mas_a3.relogo.UserTurtle){
				result.add((mas_a3.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> createOrderedUserTurtles(int number) {
		return createOrderedUserTurtles(number,null);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof mas_a3.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> userTurtles(){
		AgentSet<mas_a3.relogo.UserTurtle> a = new AgentSet<mas_a3.relogo.UserTurtle>();
		for (Object e : this.getContext().getObjects(mas_a3.relogo.UserTurtle.class)) {
			if (e instanceof mas_a3.relogo.UserTurtle){
				a.add((mas_a3.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public mas_a3.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof mas_a3.relogo.UserTurtle)
			return (mas_a3.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<mas_a3.relogo.UserTurtle> result = new AgentSet<mas_a3.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"userTurtle")){
			if (t instanceof mas_a3.relogo.UserTurtle)
			result.add((mas_a3.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<mas_a3.relogo.UserTurtle> result = new AgentSet<mas_a3.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"userTurtle")){
			if (tt instanceof mas_a3.relogo.UserTurtle)
			result.add((mas_a3.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserTurtle")
	public AgentSet<mas_a3.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<mas_a3.relogo.UserTurtle>();
		}

		Set<mas_a3.relogo.UserTurtle> total = new HashSet<mas_a3.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<mas_a3.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof mas_a3.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserLink")
	public AgentSet<mas_a3.relogo.UserLink> userLinks(){
		AgentSet<mas_a3.relogo.UserLink> a = new AgentSet<mas_a3.relogo.UserLink>();
		for (Object e : this.getContext().getObjects(mas_a3.relogo.UserLink.class)) {
			if (e instanceof mas_a3.relogo.UserLink){
				a.add((mas_a3.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserLink")
	public mas_a3.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (mas_a3.relogo.UserLink)(this.getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mas_a3.relogo.UserLink")
	public mas_a3.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}


}