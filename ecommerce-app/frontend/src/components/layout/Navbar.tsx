import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
} from "@/components/ui/navigation-menu";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Sheet,
  SheetContent,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet";
import { getCart, type CartResponse } from "@/api/apis";
import { useAuth } from "@/context/AuthContext";
import { Link } from "react-router-dom";
import { Button } from "../ui/button";
import { ShoppingCart, User, Package, LogOut } from "lucide-react";
import { useState } from "react";

const Navbar = () => {
  const { isAuthenticated, logout, user } = useAuth();
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [cart, setCart] = useState<CartResponse | null>(null);
  const [isCartLoading, setIsCartLoading] = useState(false);
  const [cartError, setCartError] = useState<string | null>(null);

  const loadCart = async () => {
    try {
      setIsCartLoading(true);
      setCartError(null);
      const response = await getCart();
      setCart(response.data);
    } catch (error) {
      console.error("Failed to load cart", error);
      setCartError("Failed to load cart. Please try again.");
    } finally {
      setIsCartLoading(false);
    }
  };

  const handleCartOpenChange = (open: boolean) => {
    setIsCartOpen(open);
    if (open) {
      loadCart();
    }
  };

  const handleLogout = async () => {
    await logout();
  };

  return (
    <nav className="border-b bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <div className="flex items-center">
            <Link to="/" className="text-2xl font-bold text-blue-600">
              Threadly
            </Link>
          </div>

          <NavigationMenu className="hidden md:block">
            <NavigationMenuList>
              <NavigationMenuItem>
                <NavigationMenuLink>
                  <Link
                    to="/"
                    className="px-3 py-2 rounded-md text-sm font-medium hover:text-blue-600"
                  >
                    Home
                  </Link>
                </NavigationMenuLink>
              </NavigationMenuItem>
              <NavigationMenuItem>
                <NavigationMenuLink>
                  <Link
                    to="/products"
                    className="px-3 py-2 rounded-md text-sm font-medium hover:text-blue-600"
                  >
                    Products
                  </Link>
                </NavigationMenuLink>
              </NavigationMenuItem>
              <NavigationMenuItem>
                <NavigationMenuTrigger className="px-3 py-2 rounded-md text-sm font-medium hover:text-blue-600">
                  Categories
                </NavigationMenuTrigger>
                <NavigationMenuContent>
                  <div className="p-4 w-48">
                    <Link
                      to="/categories/beauty"
                      className="block px-3 py-2 text-sm hover:bg-gray-100 rounded"
                    >
                      Beauty
                    </Link>
                    <Link
                      to="/categories/electronics"
                      className="block px-3 py-2 text-sm hover:bg-gray-100 rounded"
                    >
                      Electronics
                    </Link>
                    <Link
                      to="/categories/home-appliances"
                      className="block px-3 py-2 text-sm hover:bg-gray-100 rounded"
                    >
                      Home Appliances
                    </Link>
                    <Link
                      to="/categories/fashion"
                      className="block px-3 py-2 text-sm hover:bg-gray-100 rounded"
                    >
                      Fashion
                    </Link>
                  </div>
                </NavigationMenuContent>
              </NavigationMenuItem>
            </NavigationMenuList>
          </NavigationMenu>

          <div className="flex items-center space-x-4">
            {isAuthenticated ? (
              <>
                <Sheet open={isCartOpen} onOpenChange={handleCartOpenChange}>
                  <SheetTrigger asChild>
                    <Button
                      variant="ghost"
                      size="icon"
                      className="relative h-8 w-8 rounded-full"
                    >
                      <ShoppingCart className="h-5 w-5" />
                    </Button>
                  </SheetTrigger>
                  <SheetContent side="right" className="w-full sm:max-w-md">
                    <SheetHeader>
                      <SheetTitle>My Cart</SheetTitle>
                    </SheetHeader>
                    <div className="mt-4 space-y-4">
                      {isCartLoading && (
                        <p className="text-sm text-muted-foreground">
                          Loading your cart...
                        </p>
                      )}
                      {cartError && (
                        <p className="text-sm text-red-500">{cartError}</p>
                      )}
                      {!isCartLoading &&
                        !cartError &&
                        cart &&
                        cart.products.length === 0 && (
                          <p className="text-sm text-muted-foreground">
                            Your cart is empty.
                          </p>
                        )}
                      {!isCartLoading &&
                        !cartError &&
                        cart &&
                        cart.products.map((product) => (
                          <div
                            key={product.id}
                            className="flex items-center gap-4"
                          >
                            <img
                              src={product.images[0]}
                              alt={product.name}
                              className="h-16 w-16 rounded object-cover"
                            />
                            <div className="flex-1">
                              <p className="font-medium line-clamp-1">
                                {product.name}
                              </p>
                              <p className="text-sm text-muted-foreground">
                                Qty: {product.quantity}
                              </p>
                            </div>
                            <div className="text-right font-semibold">
                              ₹
                              {(product.price * product.quantity).toFixed(2)}
                            </div>
                          </div>
                        ))}
                    </div>
                    {cart && (
                      <div className="mt-6 border-t pt-4 flex items-center justify-between">
                        <span className="font-medium">Total</span>
                        <span className="text-lg font-semibold">
                          ₹{cart.totalAmount.toFixed(2)}
                        </span>
                      </div>
                    )}
                  </SheetContent>
                </Sheet>
                <DropdownMenu>
                  <DropdownMenuTrigger asChild>
                    <Button
                      variant="ghost"
                      className="relative h-8 w-8 rounded-full"
                    >
                      <Avatar className="h-8 w-8">
                        <AvatarImage src="" alt={user?.name || "User"} />
                        <AvatarFallback>
                          {user?.name ? (
                            user.name
                              .split(" ")
                              .map((n) => n[0])
                              .join("")
                              .toUpperCase()
                          ) : (
                            <User className="h-4 w-4" />
                          )}
                        </AvatarFallback>
                      </Avatar>
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent className="w-56" align="end" forceMount>
                    <DropdownMenuLabel className="font-normal">
                      <div className="flex flex-col space-y-1">
                        <p className="text-sm font-medium leading-none">
                          {user?.name || "User Account"}
                        </p>
                        <p className="text-xs leading-none text-muted-foreground">
                          {user?.email || "user@threadly.com"}
                        </p>
                      </div>
                    </DropdownMenuLabel>
                    <DropdownMenuSeparator />
                    <DropdownMenuItem asChild>
                      <Link to="/profile" className="flex items-center">
                        <User className="mr-2 h-4 w-4" />
                        <span>My Profile</span>
                      </Link>
                    </DropdownMenuItem>
                    <DropdownMenuItem asChild>
                      <Link to="/orders" className="flex items-center">
                        <Package className="mr-2 h-4 w-4" />
                        <span>Orders</span>
                      </Link>
                    </DropdownMenuItem>
                    <DropdownMenuItem asChild>
                      <Link to="/cart" className="flex items-center">
                        <ShoppingCart className="mr-2 h-4 w-4" />
                        <span>Cart</span>
                      </Link>
                    </DropdownMenuItem>
                    <DropdownMenuSeparator />
                    <DropdownMenuItem
                      onClick={handleLogout}
                      className="flex items-center"
                    >
                      <LogOut className="mr-2 h-4 w-4" />
                      <span>Log out</span>
                    </DropdownMenuItem>
                  </DropdownMenuContent>
                </DropdownMenu>
              </>
            ) : (
              <>
                <Link to="/signin">
                  <Button variant="ghost">Sign In</Button>
                </Link>
                <Link to="/signup">
                  <Button>Sign Up</Button>
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
