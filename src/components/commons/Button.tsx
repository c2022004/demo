import React from "react";
import { Link } from "react-router-dom";
import cx from "classnames";

// Define types for props
interface BaseButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  to?: string;
  href?: string;
  title?: string;
  primary?: boolean;
  outline?: boolean;
  onlyicon?: boolean;
  text?: boolean;
  disable?: boolean;
  rounded?: boolean;
  textborder?: boolean;
  small?: boolean;
  large?: boolean;
  lefticon?: React.ReactNode;
  righticon?: React.ReactNode;
  children: React.ReactNode;
  className?: string;
  background?: string;
  flex?: boolean;
  onClick?: () => void;
}

// Type for the component itself
// type ButtonProps = BaseButtonProps & {
//   ref?: React.ForwardedRef<HTMLButtonElement | HTMLAnchorElement>;
// };

// Type for the component that will be rendered (button, a, or Link)
type ComponentType = "button" | "a" | typeof Link;

const Button = React.forwardRef<
  HTMLButtonElement | HTMLAnchorElement,
  BaseButtonProps
>(
  (
    {
      className,
      to,
      href,
      // title,
      primary = false,
      outline = false,
      onlyicon = false,
      text = false,
      disable = false,
      rounded = false,
      textborder = false,
      small = false,
      large = false,
      children,
      lefticon,
      righticon,
      background,
      onClick,
      flex,
      ...passProps
    },
    ref
  ) => {
    let Component: ComponentType = "button";
    const props: any = {
      onClick,
      ...passProps,
    };

    // Handle routing logic
    if (to) {
      props.to = to;
      Component = Link;
    } else if (href) {
      props.href = href;
      Component = "a";
    }

    // Handle disabled state
    if (disable) {
      Object.keys(props).forEach((key) => {
        if (key.startsWith("on") && typeof props[key] === "function") {
          delete props[key];
        }
      });
    }

    const classes = cx("wrapper", {
      [className as string]: className,
      primary,
      outline,
      onlyicon,
      text,
      rounded,
      disable,
      textborder,
      small,
      "w-full": large,
      "bg-white": background,
      "flex justify-center items-center": flex,
    });

    props.className = classes;

    return (
      <Component {...props} ref={ref}>
        {lefticon && <span className={cx("icon")}>{lefticon}</span>}
        <span className={cx("title")}>{children}</span>
        {righticon && <span className={cx("icon")}>{righticon}</span>}
      </Component>
    );
  }
);

// Add display name for debugging purposes
Button.displayName = "Button";

export default Button;
