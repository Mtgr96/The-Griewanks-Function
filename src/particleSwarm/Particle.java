package particleSwarm;

public class Particle {
	public double[] structure;
	public double[] velocity;
	
	public void print()
	{
		for(int i = 0; i < structure.length; i++)
		{
			System.out.print(structure[i] + "\t");
		}
		System.out.println(calculateFitness());
	}
	
	public boolean better(Particle other)
	{
		if (Parameters.maximisation)
		{
			return this.calculateFitness() > other.calculateFitness();
		}
		else
		{
			return this.calculateFitness() < other.calculateFitness();
		}
	}
	
	public Particle clone()
	{
		Particle retParticle = new Particle();
		
		for(int i = 0; i < Parameters.structureLength; i++)
		{
			retParticle.structure[i] = structure[i];
			retParticle.velocity[i] = velocity[i];
		}
		return retParticle; 
	}
	
	private double product()
	{
		double prod = structure[0];
		
		for(int i = 1; i < structure.length; i++)
		{
			prod *= Math.cos(structure[i]/Math.sqrt(structure[i])) + 1;
		}
		return prod;
	}
	
	private double summation()
	{
		double sum = structure[0];
		for(int i = 1; i < structure.length; i++)
		{
			sum += Math.pow(structure[i], 2);
		}
		return sum;
	}
	
	public double calculateFitness()
	{
		return 1/4000 * summation() - product();
	}

	public Particle()
	{
		structure = new double[Parameters.structureLength];
		velocity = new double[Parameters.structureLength];
		
		for(int i = 0; i < structure.length; i++)
		{
			structure[i] = Math.random() * (Math.abs(Parameters.MAX) + Math.abs(Parameters.MIN)) - Math.abs(Parameters.MIN);
			velocity[i] = 0.0f;
		}
	}
}
