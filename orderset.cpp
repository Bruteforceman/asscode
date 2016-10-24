#include "bits/stdc++.h"
using namespace std;
struct orderset {
	struct node {
		node *l, *r;
		int value;
		int prio;

		int size;
	};
	using pnode = node*;
	pnode create(int value) {
		pnode p = (pnode) malloc(sizeof (node));
		p -> value = value;
		p -> prio = rand(); // One have to ensure that all the priorities are disinct
		p -> l = nullptr;
		p -> r = nullptr;

		p -> size = 1;
		return p;
	}

	int size(pnode t) {
		return t ? t -> size : 0;
	}
	void update(pnode &t) {
		if(t) {
			t -> size = size(t -> l) + size(t -> r) + 1;
		}
	}

	void split(pnode t, int key, pnode& a, pnode &b) {
		if(t == nullptr) {
			a = b = nullptr;
			return ;
		}
		if(t -> value <= key) {
			a = t;
			split(t -> r, key, a -> r, b);
		} else {
			b = t;
			split(t -> l, key, a, b -> l);
		}
		update(a);
		update(b);
	}
	pnode merge(pnode a, pnode b) {
		if(a == nullptr) return b;
		if(b == nullptr) return a;
		
		if(a -> prio < b -> prio) {
			a -> r = merge(a -> r, b);
			update(a);
			return a;
		} else {
			b -> l = merge(a, b -> l);
			update(b);
			return b;
		}
	}

	void add(pnode &t, pnode a) {
		if(t == nullptr) {
			t = a;
			return ;
		}	 
		if(t -> prio < a -> prio) {
			if(a -> value <= t -> value) {
				add(t -> l, a);
			} else {
				add(t -> r, a);
			}
		} else {
			split(t, a -> value, a -> l, a -> r);		
			t = a;
		}
		update(t);
	}
	void del(pnode &t, int value) {
		if(t == nullptr) return ;
		if(t -> value == value) {
	 		pnode temp = t;
	 		t = merge(t -> l, t -> r);
	 		free(temp);
		} else if (value <= t -> value) {
			del(t -> l, value);
		} else {
			del(t -> r, value);
		}
		update(t);
	}
	pnode kth(pnode t, int k) {
		if(t == nullptr) return t;
		if(size(t -> l) + 1 == k) return t;
		else if (size(t -> l) >= k) return kth(t -> l, k);
		else return kth(t -> r, k - size(t -> l) - 1); 
	}

	int order(pnode t, int value) {
		if(t == nullptr) return 0;
		if(t -> value == value) {
			return size(t -> l) + 1;
		} else if (value <= t -> value) {
			return order(t -> l, value);
		} else {
			return order(t -> r, value) + size(t -> l) + 1;
		}
	}
	void print(pnode t) {
		if(t == nullptr) return ;
		print(t -> l);
		cerr << t->value << endl;
		print(t -> r);
	}

	pnode t;
	orderset () {
		t = nullptr;
	}
	void insert(int value) {
		add(t, create(value));
	}
	void erase(int value) {
		del(t, value);
	}
	int at(int idx) {
		return kth(t, idx) -> value;
	}
	int find(int value) {
		return order(t, value);
	}
	bool present(int value) {
		int res = find(value);
		if(res > size() or res == 0) return false;
		return res == at(res);
	}
	int size() {
		return size(t);
	}
};

int main(int argc, char const *argv[])
{
	orderset s;
	s.insert(12);
	s.insert(34);
	s.insert(51);
	s.insert(7);
	s.insert(24);

	cerr << "Elements are:" << endl; 
	for(int i = 1; i <= s.size(); i++) {
		cerr << s.at(i) << " ";
	}
	cerr << endl;
	
	cerr << s.find(7) << endl;
	cerr << s.find(100) << endl;
	cerr << s.find(3) << endl;
	cerr << s.find(34) << endl;
	return 0;
}
