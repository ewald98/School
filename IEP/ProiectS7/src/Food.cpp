#include <iostream>

using namespace std;

class Food {

	private:
		string name;
		double kcalories;
		double plateDiameter;
		bool forkable;

	public:
		Food();
		Food(const string& name, double kcalories, double plateDiameter, bool forkable);

		~Food();
		void SetName(const string& name);
		string& GetName();
		void SetKcalories(double kcalories);
		double GetKcalories();
		void SetPlateDiameter(double diameter);
		double GetPlateDiameter();
		void SetForkable(bool forkable);
		bool IsForkable();

		double calculateKjoules();

};

Food::Food()
:name(),
 kcalories(),
 plateDiameter(),
 forkable()
{ }

Food::Food(const string& name, double kcalories, double plateDiameter, bool forkable)
:name(name),
 kcalories(kcalories),
 plateDiameter(plateDiameter),
 forkable(forkable)
{ }

Food::~Food() = default;

void Food::SetName(const string& name) { this->name = name; }

string& Food::GetName() { return name; }

void Food::SetKcalories(double kcalories) { this->kcalories = kcalories; }

double Food::GetKcalories() { return this->kcalories; };

void Food::SetPlateDiameter(double plateDiameter) { this->plateDiameter = plateDiameter; }

double Food::GetPlateDiameter() { return this->plateDiameter; }

void Food::SetForkable(bool forkable) { this->forkable = forkable; }

bool Food::IsForkable() { return this->forkable; }

double Food::calculateKjoules() { return this->kcalories * 4.184;}



class Drink {

	private:
		string name;

		Drink(const Drink&);
		Drink& operator = (const Drink&);

	public:
		Drink();
		Drink(const string& name);
};

Drink::Drink()
:name()
{ }

Drink::Drink(const string& name)
:name(name)
{ }

int main(void) {

	Food f("spaghete", 500, 20, true);
	cout << f.GetName() << f.GetKcalories() << endl;

	Food f2(f);		// constructor copiator
	Food f3 = f;	// operator constructor

	cout << f.GetName() << f.GetKcalories() << endl;
	cout << f.GetName() << f.GetKcalories() << endl;



	Drink d("suc");
	// Drink d1(d);
	// Drink d1 = d;

	return 0;

}